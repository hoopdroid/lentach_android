package com.lentach.util;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import com.lentach.components.Constants;
import com.roughike.bottombar.BottomBar;

/**
 * Created by Илья on 23.07.2016.
 */

public class AnimationBuilderHelper {

    public static void startIntroToolbarAnimation(Context context,Toolbar mToolbar) {

        int actionbarSize = dpToPx(context,56);
        mToolbar.setTranslationY(-actionbarSize);

        mToolbar.animate()
                .translationY(0)
                .setDuration(Constants.ANIM_DURATION_TOOLBAR)
                .setStartDelay(400);

    }

    public static void startIntroBottomAnimation(Context context, BottomBar mBottomBar) {

        int actionbarSize = dpToPx(context,56);
        mBottomBar.setTranslationY(actionbarSize);
        mBottomBar.animate()
                .translationY(mBottomBar.getY()-actionbarSize)
                .setDuration(Constants.ANIM_DURATION_TOOLBAR)
                .setStartDelay(600);

    }

    private static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics =  context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

}
