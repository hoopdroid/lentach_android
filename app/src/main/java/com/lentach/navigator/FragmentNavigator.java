package com.lentach.navigator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.lentach.R;
import com.roughike.bottombar.BottomBar;


/**
 * Fragment navigation.
 */
public class FragmentNavigator {

    public static void showFavoriteFragment(Activity activity, Fragment fragment,BottomBar bottomBar) {

        FrameLayout frameLayout = (FrameLayout) activity.findViewById(R.id.mainFrame);
        frameLayout.setVisibility(View.VISIBLE);

        bottomBar.setVisibility(View.GONE);

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) activity.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setVisibility(View.GONE);

        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.mainFrame, fragment);
        ft.commit();
    }

    public static void removeFavoriteFragment(Activity activity, Fragment fragment,BottomBar bottomBar) {

        FrameLayout frameLayout = (FrameLayout) activity.findViewById(R.id.mainFrame);
        frameLayout.setVisibility(View.GONE);

        bottomBar.setVisibility(View.VISIBLE);

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) activity.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setVisibility(View.VISIBLE);

        if (activity.getFragmentManager().findFragmentById(R.id.mainFrame) != null)
            activity.getFragmentManager().
                    beginTransaction().remove(activity.getFragmentManager().findFragmentById(R.id.mainFrame)).commit();
    }
}
