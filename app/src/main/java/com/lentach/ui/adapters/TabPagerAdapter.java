package com.lentach.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.lentach.ui.fragments.PostCommentsFragment;
import com.lentach.ui.fragments.PostInfoFragment;
import com.lentach.api.models.wallcomments.WallComment;
import com.lentach.api.models.wallpost.Post;

import java.util.ArrayList;

/**
 * Created by ilyas on 6/26/2016.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private Post mPost;
    private ArrayList<WallComment> mWallComments = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm,Post post,ArrayList<WallComment> wallComments){
        super(fm);
        mPost = post;
        mWallComments = wallComments;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                PostInfoFragment postInfoFragment = PostInfoFragment.newInstance(mPost);
                return postInfoFragment;

            case 1:
                PostCommentsFragment postCommentsFragment = PostCommentsFragment.newInstance(mWallComments,mPost.getId());
                return postCommentsFragment;

        }

        return PostInfoFragment.newInstance(mPost);

    }

    @Override
    public int getCount() {

        if(mWallComments.size()>0)
        return 2;
        else
            return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Информация";
            case 1:
                return "Комменты";
        }
        return "Dummy Tab "+(++position) ;
    }
}