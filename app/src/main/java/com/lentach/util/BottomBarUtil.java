package com.lentach.util;

import android.app.Activity;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;

import com.lentach.router.ActivityRouter;
import com.roughike.bottombar.BottomBar;

/**
 * Created by ilyasavin on 11/22/16.
 */

public class BottomBarUtil {

    public static void showNoAuthSnackBar(ViewGroup coordinatorLayout, final Activity activity){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Вы не авторизованы!", Snackbar.LENGTH_LONG)
                .setAction("Войти", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActivityRouter.startVKPermissionActivity(activity);
                    }
                });

        snackbar.show();
    }


    public static void showBottomBar(BottomBar mBottomBar, CoordinatorLayout mCoordinatorLayout, boolean show){
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mBottomBar.getLayoutParams();
        CoordinatorLayout.Behavior behavior2 = params.getBehavior();
        if (behavior2 != null) {
            behavior2.onNestedFling(mCoordinatorLayout, mBottomBar, null, 0, show ? -10000: 10000, true);
        }
    }

}
