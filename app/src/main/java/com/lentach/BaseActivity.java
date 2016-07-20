package com.lentach;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;

import butterknife.ButterKnife;
import io.realm.Realm;


/**
 * The base activity of application is used to set ButterKnife binding
 */
public class BaseActivity extends AppCompatActivity {

    public static Realm realm;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-1178779645409927~7230973298");
    }

}
