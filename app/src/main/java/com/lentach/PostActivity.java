package com.lentach;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.lentach.adapters.TabPagerAdapter;
import com.lentach.components.Constants;
import com.lentach.data.DataService;
import com.lentach.db.RealmUtils;
import com.lentach.models.realm.PostRealmModel;
import com.lentach.models.wallcomments.WallComment;
import com.lentach.models.wallcomments.users.User;
import com.lentach.models.wallpost.Post;
import com.lentach.navigator.ActivityNavigator;
import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiWall;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static android.view.View.GONE;

public class PostActivity extends BaseActivity {

    @Bind(R.id.postImage)
    ImageView postImage;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.slider)
    SliderLayout sliderLayout;
    TabPagerAdapter tabPagerAdapter;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.detail_tabs)
    TabLayout mTabLayout;

    DefaultSliderView textSliderView;


    private Post mPost;
    private ArrayList<WallComment> mWallComments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mPost = getIntent().getParcelableExtra(Constants.POST_EXTRA);
        int  a = 5;
        getCommentsData();

        initViewElements();



    }

    private void initTabs() {
        tabPagerAdapter=new TabPagerAdapter(getSupportFragmentManager(),mPost,mWallComments);
        mViewPager.setAdapter(tabPagerAdapter);
        mTabLayout.setTabsFromPagerAdapter(tabPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //getCommentsData();
            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){

                } else {

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initViewElements() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mPost.getPhotoAmount() == 1) {
                postImage.setVisibility(View.VISIBLE);
                postImage.setTransitionName(Constants.ARTIST_TRANSITION);
                Picasso.with(this).load(mPost.getAttachments().get(0).
                        getPhoto().getPhoto604()).placeholder(R.drawable.lentach_placeholder).
                        error(R.drawable.lentach_placeholder).into(postImage);
                sliderLayout.setVisibility(GONE);
            } else {
                sliderLayout.setTransitionName(Constants.ARTIST_TRANSITION);
                initSlider();
            }
        }
        else {
            if (mPost.getPhotoAmount() == 1) {
                postImage.setVisibility(View.VISIBLE);
                sliderLayout.setVisibility(GONE);
                Picasso.with(this).load(mPost.getAttachments().get(0).getPhoto().getPhoto604()).
                        placeholder(R.drawable.lentach_placeholder).
                        error(R.drawable.lentach_placeholder).into(postImage);
            } else {
                initSlider();
            }
        }

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.showOverflowMenu();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    private void initSlider() {
        int sliderCount = 0;
        if(mPost.getAttachments()!=null)
        for (int i = 0; i < mPost.getAttachments().size() ; i++) {
            if(mPost.getAttachments().get(i).getType()!=null)
            if(mPost.getAttachments().get(i).getType().equals("photo")){
                final int temp = i ;
                textSliderView = new DefaultSliderView(this);
                textSliderView.image(mPost.getAttachments().get(i).getPhoto().getPhoto604()).setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {

                        ActivityNavigator.startPhotoActivity(slider.getContext(),mPost.getAttachments().get(temp).getPhoto().getPhoto604());
                    }
                });
                sliderLayout.addSlider(textSliderView);
                sliderCount++;}

        }
        if(sliderCount==1){
        sliderLayout.stopAutoCycle();
        sliderLayout.removeAllSliders();}

        else {
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setDuration(4000);

        }

    }


    private void getCommentsData() {

        DataService.init().getCommentsFromServer(PostActivity.this,new DataService.onRequestCommentsResult() {
            @Override
            public void onRequestCommentsResult(List<WallComment> posts, List<User> arr3) {
                mWallComments = (ArrayList<WallComment>) posts;

                initTabs();
            }

        },mPost.getId());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.star) {
            if(RealmUtils.addToFavorites(realm,mPost)){
                Toast.makeText(getApplicationContext(),"ADD",Toast.LENGTH_SHORT).show();
            return true;}
            else{
                RealmUtils.removeFromFavorites(realm,mPost);
                Toast.makeText(getApplicationContext(),"DELETE",Toast.LENGTH_SHORT).show();
                return true;}

        }

        if(id==R.id.share){
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, mPost.getText());
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent,"Поделиться лентачом"));


        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.postImage)
    void startPhoto(View view) {
        ActivityNavigator.startPhotoActivity(view.getContext(),mPost.getAttachments().get(mPost.getPhotoAmount()-1).getPhoto().getPhoto604());
    }


}
