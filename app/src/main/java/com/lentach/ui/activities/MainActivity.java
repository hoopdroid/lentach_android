package com.lentach.ui.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.lentach.BuildConfig;
import com.lentach.R;
import com.lentach.ui.adapters.HotPostsRVAdapter;
import com.lentach.ui.adapters.PostsRVAdapter;
import com.lentach.ui.adapters.TopCommentsOfDayRVAdapter;
import com.lentach.ui.adapters.VideoPostsRVAdapter;
import com.lentach.ui.components.BestPostsController;
import com.lentach.ui.components.Constants;
import com.lentach.ui.components.EndlessRecyclerOnScrollListener;
import com.lentach.ui.components.NewPostsController;
import com.lentach.util.BottomBarUtil;
import com.lentach.util.PostsLikeComporator;
import com.lentach.ui.components.TopCommentsController;
import com.lentach.api.DataService;
import com.lentach.api.vkApi.VkApiManager;
import com.lentach.ui.fragments.FavoritesFragment;
import com.lentach.api.models.comment.Comment;
import com.lentach.api.models.wallpost.Post;
import com.lentach.router.ActivityRouter;
import com.lentach.router.FragmentRouter;
import com.lentach.util.AnimationBuilderHelper;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.rv)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.mainFrame)
    FrameLayout mFragmentFrame;
    @Bind(R.id.search_view)
    MaterialSearchView mSearchView;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;

    private BottomBar mBottomBar;

    private FirebaseRemoteConfig remoteConfig;

    MenuItem mSearchItemView;
    String username;

    private TopCommentsOfDayRVAdapter mTopCommentsOfDayRVAdapter;
    private TopCommentsController mTopCommentsController;
    private NewPostsController mNewPostsController;
    private BestPostsController mBestPostsController;

    ArrayList<Comment> comments;
    PostsRVAdapter mPostsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTopCommentsController = new TopCommentsController();
        mNewPostsController = new NewPostsController();
        mBestPostsController = new BestPostsController();

        initViewElements(savedInstanceState, mToolbar);

        getNewPostsData(MainActivity.this);
        VkApiManager.getUserInfo(this);

        FirebaseRemoteConfigSettings remoteConfigSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();

        remoteConfig = FirebaseRemoteConfig.getInstance();
        remoteConfig.setConfigSettings(remoteConfigSettings);
        remoteConfig.setDefaults(R.xml.default_config);

        getFirebaseConfigData();

    }

    private void showAdvertisment(String admob) {
        if(admob.equals("true")) {
            AdView mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.setVisibility(View.VISIBLE);
            mAdView.loadAd(adRequest);}
        else return;
    }


    public void updateHotPosts(int span) {
        RecyclerView.LayoutManager layoutManager = null;


        GridLayoutManager dynamicGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(dynamicGridLayoutManager);


    }


    public void updateBestPosts() {
        RecyclerView.LayoutManager layoutManager = null;


        GridLayoutManager dynamicGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(dynamicGridLayoutManager);


    }

    public void updateNewsPosts(int span) {
        RecyclerView.LayoutManager layoutManager = null;


        LinearLayoutManager dynamicGridLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(dynamicGridLayoutManager);

        setOnLoadScrollListener(dynamicGridLayoutManager);


    }


    private void initViewElements(Bundle savedInstanceState, Toolbar toolbar) {

        mToolbar.setTitle(R.string.app_name_toolbar);
        setSupportActionBar(mToolbar);
        initDrawer(toolbar, this);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getPostsFromSearch(MainActivity.this, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                mBottomBar.setVisibility(View.GONE);
            }

            @Override
            public void onSearchViewClosed() {

                mBottomBar.setVisibility(View.VISIBLE);

            }
        });


        mBottomBar = BottomBar.attachShy((CoordinatorLayout) findViewById(R.id.coordinatorLayout),
                findViewById(R.id.rv), savedInstanceState);
        mBottomBar.noTabletGoodness();
        mBottomBar.setItems(R.menu.bottom_menu);
        mBottomBar.getBar().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mBottomBar.setActiveTabColor("#ffffff");

        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemOne) {

                    getNewPostsData(MainActivity.this);
                }

                if (menuItemId == R.id.bottomBarItemTwo) {

                    getHotPostsData();

                }

                if (menuItemId == R.id.bottomBarItemThree) {

                    getBestPosts(MainActivity.this);

                }

            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemOne) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });

        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(true);

        BottomBarUtil.showBottomBar(mBottomBar,mCoordinatorLayout,true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        AnimationBuilderHelper.startIntroToolbarAnimation(this, mToolbar);
        AnimationBuilderHelper.startIntroBottomAnimation(this, mBottomBar);
        mSearchItemView = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(mSearchItemView);
        mSearchView.setVoiceSearch(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        if (mBottomBar.getCurrentTabPosition() == 0)
            getNewPostsData(MainActivity.this);
        if (mBottomBar.getCurrentTabPosition() == 1)
            getHotPostsData();
        if (mBottomBar.getCurrentTabPosition() == 2)
            getBestPosts(MainActivity.this);

            mSwipeRefreshLayout.setRefreshing(true);
    }


    public void initDrawer(android.support.v7.widget.Toolbar toolbar, final Activity activity) {

        PrimaryDrawerItem itemHome = new PrimaryDrawerItem().withName("Главная").withIcon(R.drawable.ic_home_grey600_24dp);
        PrimaryDrawerItem itemHotPosts = new PrimaryDrawerItem().withName("Избранное").withIcon(R.drawable.ic_star_grey600_24dp);
        PrimaryDrawerItem itemBestComments = new PrimaryDrawerItem().withName("Лучшие комментарии").withIcon(R.drawable.ic_comment_account_grey600_24dp);
        PrimaryDrawerItem itemVideo= new PrimaryDrawerItem().withName("Видач").withIcon(R.drawable.ic_live_tv_grey_600_24dp);
        PrimaryDrawerItem itemChat = new PrimaryDrawerItem().withName("ЛентаЧАТ").withIcon(R.drawable.ic_comment_alert_grey600_24dp);
        PrimaryDrawerItem itemSettings = new PrimaryDrawerItem().withName("О проекте").withIcon(R.drawable.ic_settings_grey600_24dp);

        AccountHeader headerResult;
        username = "Юзер Лентача";
        SharedPreferences sharedPreferences = getSharedPreferences("Default", MODE_PRIVATE);
        if (VKAccessToken.currentToken() != null)
            username = sharedPreferences.getString("first_name", "Юзер") + " " + sharedPreferences.getString("last_name", "Лентача");

        //initialize and create the image loader logic
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }
        });

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_drawer)
                .addProfiles(
                        new ProfileDrawerItem().withName(username).withIcon(sharedPreferences.getString("avatar_image", "")),
                        new ProfileSettingDrawerItem().withName("Настройки пользователя").withIcon
                                (R.drawable.settings).withIdentifier(1000)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        if (profile.getIdentifier() == 1000)
                            ActivityRouter.startVKPermissionActivity(MainActivity.this);

                        else
                            ActivityRouter.startUserActivity(MainActivity.this, null, username);

                        return false;
                    }
                })
                .withSelectionListEnabledForSingleProfile(false)
                .build();

        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withTranslucentStatusBar(false)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        itemHome,
                        itemHotPosts,
                        itemVideo,
                        itemBestComments,
                        itemChat,
                        new DividerDrawerItem(),
                        itemSettings
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                mToolbar.setTitle("¯|_ (ツ) _ |¯");
                                mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                                mSearchItemView.setVisible(true);
                                mSwipeRefreshLayout.setEnabled(true);
                                if(mPostsRVAdapter.getItemCount()>0)
                                mRecyclerView.setAdapter(mPostsRVAdapter);
                                //getNewPostsData(activity);
                                FragmentRouter.removeFavoriteFragment(activity, FavoritesFragment.newInstance(), mBottomBar);
                                break;
                            case 2:
                                mToolbar.setTitle("Избранное");
                                mSwipeRefreshLayout.setVisibility(View.GONE);
                                FragmentRouter.showFavoriteFragment(MainActivity.this, FavoritesFragment.newInstance(), mBottomBar);
                                mSearchItemView.setVisible(false);
                                break;
                            case 3:
                                mBottomBar.setVisibility(View.GONE);
                                mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                                mSwipeRefreshLayout.setEnabled(false);
                                getVideoPostsData(MainActivity.this);
                                mToolbar.setTitle("Видач");
                                mSearchItemView.setVisible(false);
                                break;
                            case 4:
                                mSwipeRefreshLayout.setEnabled(false);
                                mToolbar.setTitle("Лучшие комментарии");
                                mBottomBar.setVisibility(View.GONE);
                                mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                                getCommentsOfDay();
                                FragmentRouter.removeFavoriteFragment(activity, FavoritesFragment.newInstance(), mBottomBar);
                                mSearchItemView.setVisible(false);
                                break;
                            case 5:
                                ActivityRouter.startChatActivity(activity);
                                break;
                            case 7:
                                ActivityRouter.startSettingsActivity(activity);
                                break;
                        }
                        return false;
                    }
                })
                .build();

    }

    protected void getBestPosts(final Activity activity) {
        mToolbar.setTitle(getResources().getString(R.string.app_name_toolbar));
        mSwipeRefreshLayout.setRefreshing(true);
        mBottomBar.setVisibility(View.VISIBLE);
        DataService.init().getBestPostsFromServer(new DataService.onRequestWebApiResult() {
            public void onRequestWebApiResult(List<Post> posts) {

                mSwipeRefreshLayout.setRefreshing(false);

                mBestPostsController.setmNewPostsList(posts);

                updateHotPosts(1);

                HotPostsRVAdapter mPostsRVAdapter = new HotPostsRVAdapter(getApplicationContext(),
                        posts);

                mRecyclerView.setAdapter(mPostsRVAdapter);

                AnimationBuilderHelper.startIntroAnimation(activity, mRecyclerView);

            }
        });
    }

    protected void getNewPostsData(final Activity activity) {
        mToolbar.setTitle(getResources().getString(R.string.app_name_toolbar));
        mSwipeRefreshLayout.setRefreshing(true);
        DataService.init().getPostsFromWall(activity, new DataService.onRequestResult() {
            @Override
            public void onRequestResult(List<Post> posts) {

                mNewPostsController.setNewPostsList(posts);

                mPostsRVAdapter = new PostsRVAdapter(activity,
                        posts);
                mNewPostsController.setOffset(mNewPostsController.getOffset() + Constants.POSTS_COUNT);
                updateNewsPosts(3);
                mRecyclerView.setAdapter(mPostsRVAdapter);
                mSwipeRefreshLayout.setRefreshing(false);

                AnimationBuilderHelper.startIntroAnimation(activity, mRecyclerView);
            }

        }, Constants.POSTS_COUNT, 0);
    }

    protected void getVideoPostsData(final Activity activity) {
        mSwipeRefreshLayout.setRefreshing(true);
        DataService.init().getVideoPostsFromWall(activity, new DataService.onRequestResult() {
            @Override
            public void onRequestResult(List<Post> posts) {

                mNewPostsController.setNewPostsList(posts);

                VideoPostsRVAdapter mPostsRVAdapter = new VideoPostsRVAdapter(activity,
                        posts);
                mNewPostsController.setOffset(mNewPostsController.getOffset() + Constants.POSTS_COUNT);
                updateNewsPosts(3);
                mRecyclerView.setAdapter(mPostsRVAdapter);
                mSwipeRefreshLayout.setRefreshing(false);

                AnimationBuilderHelper.startIntroAnimation(activity, mRecyclerView);
            }

        }, 100, 0);
    }

    protected void getPostsFromSearch(final Activity activity, String query) {
        mToolbar.setTitle(getResources().getString(R.string.app_name_toolbar));
        mSwipeRefreshLayout.setRefreshing(true);
        mBottomBar.setVisibility(View.VISIBLE);
        DataService.init().searchOnWallFromQuery(activity, new DataService.onRequestResult() {
            @Override
            public void onRequestResult(List<Post> posts) {


                PostsRVAdapter mArtistsRVAdapter = new PostsRVAdapter(activity,
                        posts);

                updateNewsPosts(1);
                mRecyclerView.setAdapter(mArtistsRVAdapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

        }, query);
    }

    private void getHotPostsData() {
        mToolbar.setTitle(getResources().getString(R.string.app_name_toolbar));
        mSwipeRefreshLayout.setRefreshing(true);
        mBottomBar.setVisibility(View.VISIBLE);
        DataService.init().getPostsFromWall(MainActivity.this, new DataService.onRequestResult() {
            @Override
            public void onRequestResult(List<Post> posts) {

                Collections.sort(posts, new PostsLikeComporator());

                updateNewsPosts(1);
                PostsRVAdapter mArtistsRVAdapter = new PostsRVAdapter(getApplicationContext(),
                        posts);

                mRecyclerView.setAdapter(mArtistsRVAdapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }


        }, 100, 0);
    }


    private void setOnLoadScrollListener(LinearLayoutManager linearLayoutManager) {
        mRecyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                // do something...
                DataService.init().getPostsFromWall(MainActivity.this, new DataService.onRequestResult() {
                    @Override
                    public void onRequestResult(List<Post> posts) {


                        mSwipeRefreshLayout.setRefreshing(false);

                        mNewPostsController.addNewPosts(posts);

                        mPostsRVAdapter.notifyDataSetChanged();

                        mNewPostsController.setOffset(mNewPostsController.getOffset() + Constants.POSTS_COUNT);


                    }

                }, Constants.POSTS_COUNT, mNewPostsController.getOffset());


            }
        });
    }


    protected void getCommentsOfDay() {

        mSwipeRefreshLayout.setRefreshing(true);
        DataService.init().getCommentsOfDayFromServer(new DataService.onRequestCommentsOFDayResult() {
            int a = 5;

            @Override
            public void onRequestCommentsResult(List<Comment> wallComments) {
                comments = new ArrayList<Comment>();
                comments.addAll(wallComments);

                mTopCommentsController.setArtistsList(comments);

                mTopCommentsOfDayRVAdapter = new TopCommentsOfDayRVAdapter(getApplicationContext(),
                        comments);
                mSwipeRefreshLayout.setRefreshing(false);

                mRecyclerView.setAdapter(mTopCommentsOfDayRVAdapter);

            }

        });
    }

    private void getFirebaseConfigData() {

        long cacheExpiration = 3600;

        if (remoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        remoteConfig.fetch(cacheExpiration)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        remoteConfig.activateFetched();
                        String admob = remoteConfig.getString(Constants.BUILD_CONFIG_AD);
                        showAdvertisment(admob);
                        Toast.makeText(MainActivity.this, admob, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
