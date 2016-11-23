package com.lentach.ui.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.lentach.R;
import com.lentach.api.models.webapipost.WebAPIPost;
import com.lentach.ui.adapters.TabPagerAdapter;
import com.lentach.ui.components.Constants;
import com.lentach.api.DataService;
import com.lentach.api.vkApi.VkApiManager;
import com.lentach.repository.RealmUtils;
import com.lentach.api.models.wallcomments.WallComment;
import com.lentach.api.models.wallcomments.users.User;
import com.lentach.api.models.wallpost.Post;
import com.lentach.router.ActivityRouter;
import com.lentach.util.AnimationBuilderHelper;
import com.lentach.util.BottomBarUtil;
import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.model.VKApiVideo;

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
    @Bind(R.id.videoButton)
    ImageView mVideoButton;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    String videoUrl;
    int isVidach = 0;

    DefaultSliderView textSliderView;
    boolean isPostLikedByUser = false;

    private Post mPost;
    private WebAPIPost mPostWeb;
    private int isWeb;
    private ArrayList<WallComment> mWallComments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        isWeb = getIntent().getIntExtra("Web",0);

        mPost = getIntent().getParcelableExtra(Constants.POST_EXTRA);

        isVidach = getIntent().getIntExtra("Video",0);
        if(isVidach==0)
            getCommentsData();


        initViewElements();

        String userIdString = "0";
        if(VKAccessToken.currentToken()!=null)
        userIdString = VKAccessToken.currentToken().userId;
        VkApiManager.init().isPostLiked(new VkApiManager.onIsLikedResult() {
            @Override
            public void onIsLikedResult(boolean isLiked) {

                isPostLikedByUser = isLiked;

                if(isLiked)
                {
                    fab.setImageResource(R.drawable.ic_heart_white_24dp);
                }
                else
                    fab.setImageResource(R.drawable.ic_heart_outline_white_24dp);

            }
        },this,mPost.getId(), Integer.parseInt(userIdString));



    }

    private void initTabs() {
        tabPagerAdapter=new TabPagerAdapter(getSupportFragmentManager(),mPost,mWallComments);
        mViewPager.setAdapter(tabPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
     //   mTabLayout.getTabAt(0).setIcon(R.drawable.ic_home_white_24dp);
       // mTabLayout.getTabAt(1).setIcon(R.drawable.ic_comment_account_white_24dp);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

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

        AnimationBuilderHelper.startIntroFabAnimation(this,fab);

        selectTypeAttachmentAndInitView();

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

                if(!isPostLikedByUser){
                    VkApiManager.init().addLikeToPost(getApplicationContext(), new VkApiManager.onAddLikesResult() {
                                @Override
                                public void onAddLikesResult(int likesCount) {
                                    fab.setImageResource(R.drawable.ic_heart_white_24dp);
                                }
                            },
                            new VkApiManager.onAddLikesResultFail() {
                                @Override
                                public void onAddLikesResultFail(String message) {
                                    BottomBarUtil.showNoAuthSnackBar(coordinatorLayout,PostActivity.this);
                                }
                            },
                            mPost.getId()
                    );}

                else {

                    VkApiManager.init().deleteLikeFromPost(getApplicationContext(), new VkApiManager.onAddLikesResult() {
                                @Override
                                public void onAddLikesResult(int likesCount) {
                                    fab.setImageResource(R.drawable.ic_heart_outline_white_24dp);
                                }
                            },
                    new VkApiManager.onAddLikesResultFail() {
                        @Override
                        public void onAddLikesResultFail(String message) {
                            BottomBarUtil.showNoAuthSnackBar(coordinatorLayout,PostActivity.this);
                        }
                    },
                            mPost.getId()
                    );

                }



            }
        });


    }

    private void selectTypeAttachmentAndInitView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mPost.getAttachments()!=null&&mPost.getAttachments().size()>0)
                if(mPost.getAttachments().get(0).getType().equals("photo")) {
                    if (mPost.getPhotoAmount() == 1) {
                      postImage.setTransitionName(Constants.IMAGE_TRANSITION);
                      loadPhotoAndDisableSlider();
                   } else {
                         sliderLayout.setTransitionName(Constants.IMAGE_TRANSITION);
                         initSlider();
                 }
            }
                else if (mPost.getAttachments()!=null&&mPost.getAttachments().get(0).getType().equals("video")){
                loadVideoAndDisableSlider();
                mVideoButton.setVisibility(View.VISIBLE);
                }
        }
        else {
            if (mPost.getPhotoAmount() == 1) {
                loadPhotoAndDisableSlider();
            } else {
                initSlider();
            }
        }
    }

    private void loadVideoAndDisableSlider() {


        DataService.init().getVideoFromId(this, new DataService.onRequestVideoFromId() {
            @Override
            public void onRequestVideoFromId(VKApiVideo vkApiVideo) {
                int a  = 5;
                if(mPost.getAttachments().get(0).getVideo().getOwnerId()==getResources().getInteger(R.integer.group_id))
                    videoUrl = vkApiVideo.player;
                else
                    videoUrl = "";

            }
        },mPost.getAttachments().get(0).getVideo().getId());

        postImage.setVisibility(View.VISIBLE);
        Picasso.with(this).load(mPost.getAttachments().get(0).
                getVideo().getPhoto320()).placeholder(R.drawable.lentach_placeholder).
                error(R.drawable.lentach_placeholder).into(postImage);
        sliderLayout.setVisibility(GONE);
    }

    private void loadPhotoAndDisableSlider() {
        postImage.setVisibility(View.VISIBLE);
        Picasso.with(this).load(mPost.getAttachments().get(0).
                getPhoto().getPhoto604()).placeholder(R.drawable.lentach_placeholder).
                error(R.drawable.lentach_placeholder).into(postImage);
        sliderLayout.setVisibility(GONE);
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

                        ActivityRouter.startPhotoActivity(
                                PostActivity.this,mPost.getAttachments().get(temp).getPhoto().getPhoto604(),
                                postImage,
                                Constants.IMAGE_TRANSITION);
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

        DataService.init().getCommentsFromWallPost(PostActivity.this,new DataService.onRequestCommentsResult() {
            @Override
            public void onRequestCommentsResult(List<WallComment> posts, List<User> arr3) {
                mWallComments = (ArrayList<WallComment>) posts;

                initTabs();
            }

        },mPost.getId(),20);

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
                Snackbar.make(coordinatorLayout,"Добавлено в избранное",Snackbar.LENGTH_SHORT).show();
            return true;}
            else{
                RealmUtils.removeFromFavorites(realm,mPost);
                Snackbar.make(coordinatorLayout,"Удалено из избранного",Snackbar.LENGTH_SHORT).show();
                return true;}

        }

        if(id==R.id.vk_link){
            String url = "https://vk.com/true_lentach?w=wall"+getResources().getInteger(R.integer.group_id)+"_"+mPost.getId().toString();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.postImage)
    void startPhoto(View view) {
        if(mPost.getAttachments().get(0).getType().equals("photo"))
        ActivityRouter.startPhotoActivity(PostActivity.this,
                mPost.getAttachments().get(mPost.getPhotoAmount()-1).getPhoto().getPhoto604(),
                postImage,
                Constants.IMAGE_TRANSITION);
        else
        {
            /*Intent intent = new Intent(Intent.ACTION_SEARCH);
            intent.setPackage("com.google.android.youtube");
            intent.putExtra("query", mPost.getAttachments().get(0).getVideo().getTitle());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            */

            if(!videoUrl.equals(""))
            ActivityRouter.startVideoActivity(PostActivity.this,videoUrl);
            else {Intent intent = new Intent(Intent.ACTION_SEARCH);
            intent.setPackage("com.google.android.youtube");
            intent.putExtra("query", mPost.getAttachments().get(0).getVideo().getTitle());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);}

        }
    }


    private void fadeIn() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(fab, "alpha", 0f, 1f);
        objectAnimator.setDuration(500L);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                fadeOut();
            }
        });
        objectAnimator.start();
    }

    private void fadeOut() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(fab, "alpha", 1f, 0f);
        objectAnimator.setDuration(500L);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
        objectAnimator.start();
    }


}
