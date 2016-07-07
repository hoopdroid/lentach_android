package com.lentach.views;

import android.app.Activity;
import android.view.View;

import com.lentach.BaseActivity;
import com.lentach.MainActivity;
import com.lentach.R;
import com.lentach.db.RealmUtils;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


/**
 * A implementation of Navigaton Drawer using https://github.com/mikepenz/MaterialDrawer library.
 */
public class MaterialDrawer {

    public void initDrawer(android.support.v7.widget.Toolbar toolbar, final Activity activity) {

        PrimaryDrawerItem itemHome = new PrimaryDrawerItem().withName("Главная").withIcon(R.drawable.ic_heart_grey600_24dp);
        PrimaryDrawerItem itemHotPosts = new PrimaryDrawerItem().withName("Избранное").withIcon(R.drawable.ic_star_grey600_24dp);
        PrimaryDrawerItem itemBestComments = new PrimaryDrawerItem().withName("Топовые комментарии").withIcon(R.drawable.ic_comment_alert_grey600_24dp);
        PrimaryDrawerItem itemRadio = new PrimaryDrawerItem().withName("#РадиоЛентач").withIcon(R.drawable.ic_bookmark_music_grey600_24dp);
        PrimaryDrawerItem itemSettings = new PrimaryDrawerItem().withName("Настройки").withIcon(R.drawable.ic_settings_grey600_24dp);

        AccountHeader headerResult;

        headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.color.primary_dark)
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
                              //  getNewPostsData(activity);
                                break;
                            case 2:
                                //getFavorites();
                                break;
                            case 4:
                                break;
                        }
                        return false;
                    }
                })
                .build();
    }
}
