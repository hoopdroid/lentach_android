package com.lentach;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lentach.adapters.CommentsRVAdapter;
import com.lentach.adapters.PostsRVAdapter;
import com.lentach.components.PostsLikeComporator;
import com.lentach.data.DataService;
import com.lentach.db.RealmUtils;
import com.lentach.models.comment.Comment;
import com.lentach.models.realm.PostRealmModel;
import com.lentach.models.wallcomments.WallComment;
import com.lentach.models.wallpost.Attachment;
import com.lentach.models.wallpost.Likes;
import com.lentach.models.wallpost.Photo;
import com.lentach.models.wallpost.Post;
import com.lentach.navigator.ActivityNavigator;
import com.lentach.views.MaterialDrawer;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.vk.sdk.VKAccessToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import io.realm.RealmList;

public class MainActivity extends BaseActivity implements  SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.rv)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    BottomBar mBottomBar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        initViewElements(savedInstanceState, toolbar);

        getNewPostsData(MainActivity.this);

    }

    protected void getNewPostsData(final Activity activity) {
        toolbar.setTitle(getResources().getString(R.string.app_name));
        swipeRefreshLayout.setRefreshing(true);
        mBottomBar.setVisibility(View.VISIBLE);
        DataService.init().getPostsFromWall(activity,new DataService.onRequestResult() {
            @Override
            public void onRequestResult(List<Post> posts) {


                PostsRVAdapter mArtistsRVAdapter = new PostsRVAdapter(activity,
                        posts);

                updateRecyclerView(1);
                mRecyclerView.setAdapter(mArtistsRVAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }


        });
    }

    protected void getCommentsOfDay(){

        DataService.init().getDataFromServer(new DataService.onRequestCommentsOFDayResult() {
            int a = 5;
            @Override
            public void onRequestCommentsResult(List<Comment> wallComments) {
                //  CommentsRVAdapter mCommentsRVAdapter = new CommentsRVAdapter(getApplicationContext(),
                //        wallComments);
                updateRecyclerView(1);
                //mRecyclerView.setAdapter(mCommentsRVAdapter);
                swipeRefreshLayout.setRefreshing(false);

                toolbar.setTitle("Комментарии дня");
                mBottomBar.setVisibility(View.GONE);
            }



        });


        int a =5;
    }

    protected void getFavorites(){

        ArrayList<PostRealmModel> list = RealmUtils.getAllPostsFromDB(realm);
        ArrayList<Post> postList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            ArrayList<Attachment> simpleAttachList = new ArrayList<>();
            simpleAttachList.add(new Attachment("photo",new Photo(list.get(i).getPhotoAttach())));

            postList.add(new Post(list.get(i).getId(),
                    list.get(i).getFromId(),
                    list.get(i).getOwnerId(),
                    list.get(i).getPostType(),
                    list.get(i).getDate(),
                    list.get(i).getText(),
                    list.get(i).getIsPinned(),
                    simpleAttachList,
                    new Likes(list.get(i).getLikes()
                    )));

        }

        PostsRVAdapter mArtistsRVAdapter = new PostsRVAdapter(getApplicationContext(),
                postList);

        updateRecyclerView(2);
        mRecyclerView.setAdapter(mArtistsRVAdapter);
        swipeRefreshLayout.setRefreshing(false);

        toolbar.setTitle("Избранное");
        mBottomBar.setVisibility(View.GONE);

        int a =5;
    }

    private void updateRecyclerView(int span) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, span);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    private void getHotPostsData() {
        toolbar.setTitle(getResources().getString(R.string.app_name));
        swipeRefreshLayout.setRefreshing(true);
        mBottomBar.setVisibility(View.VISIBLE);
        DataService.init().getPostsFromWall(MainActivity.this,new DataService.onRequestResult() {
            @Override
            public void onRequestResult(List<Post> posts) {

                Collections.sort(posts,new PostsLikeComporator());

                updateRecyclerView(1);
                PostsRVAdapter mArtistsRVAdapter = new PostsRVAdapter(getApplicationContext(),
                        posts);

                mRecyclerView.setAdapter(mArtistsRVAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }


        });
    }

    private void initViewElements(Bundle savedInstanceState, Toolbar toolbar) {

        initDrawer(toolbar, this);

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setOnRefreshListener(this);


        mBottomBar = BottomBar.attachShy((CoordinatorLayout) findViewById(R.id.coordinatorLayout),
                findViewById(R.id.rv), savedInstanceState);
        mBottomBar.setItems(R.menu.bottom_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemOne) {

                    getNewPostsData(MainActivity.this);
                }
                if (menuItemId == R.id.bottomBarItemTwo) {

                    getHotPostsData();

                }

            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemOne) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });

        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        mBottomBar.setActiveTabColor(R.color.accent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }

    @Override
    public void onRefresh() {
        if(mBottomBar.getCurrentTabPosition()==0)
            getNewPostsData(MainActivity.this);
        if(mBottomBar.getCurrentTabPosition()==1)
            getHotPostsData();
        swipeRefreshLayout.setRefreshing(true);
    }


    public void initDrawer(android.support.v7.widget.Toolbar toolbar, final Activity activity) {

        PrimaryDrawerItem itemHome = new PrimaryDrawerItem().withName("Главная").withIcon(R.drawable.ic_heart_grey600_24dp);
        PrimaryDrawerItem itemHotPosts = new PrimaryDrawerItem().withName("Избранное").withIcon(R.drawable.ic_star_grey600_24dp);
        PrimaryDrawerItem itemBestComments = new PrimaryDrawerItem().withName("Комментарии дня").withIcon(R.drawable.ic_comment_alert_grey600_24dp);
        PrimaryDrawerItem itemRadio = new PrimaryDrawerItem().withName("#РадиоЛентач").withIcon(R.drawable.ic_bookmark_music_grey600_24dp);
        PrimaryDrawerItem itemSettings = new PrimaryDrawerItem().withName("Настройки").withIcon(R.drawable.ic_settings_grey600_24dp);

        AccountHeader headerResult;
        String username="Юзер Лентача";
        if(VKAccessToken.currentToken()!=null)
            username = VKAccessToken.currentToken().email;

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.primary)
                .addProfiles(
                        new ProfileDrawerItem().withName(username).withIcon(getResources().getDrawable(R.drawable.ic_account_white_48dp))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        return false;
                    }
                })
                .withSelectionListEnabledForSingleProfile(false)
                .build();

        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        itemHome,
                        itemHotPosts,
                        itemBestComments,
                        itemRadio,
                        new DividerDrawerItem(),
                        itemSettings
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                mBottomBar.selectTabAtPosition(0,true);
                                getNewPostsData(activity);
                                break;
                            case 3:
                                getCommentsOfDay();
                                break;
                            case 2:
                                getFavorites();
                                break;
                            case 6:
                                ActivityNavigator.startVKPermissionActivity(activity);
                                break;
                        }
                        return false;
                    }
                })
                .build();
    }


}
