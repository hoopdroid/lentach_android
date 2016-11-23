package com.lentach.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.lentach.R;
import com.lentach.ui.fragments.SettingsFragment;

import butterknife.Bind;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class SettingsActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.settingsFragment)
    FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        mToolbar.setTitle("О проекте");
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Element versionElement = new Element();
        versionElement.setTitle("Версия 1.0");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription(getString(R.string.about_page_description_text))
                .setImage(R.drawable.lentach_web)
                .addItem(versionElement)
                .addGroup("Контакты")
                .addEmail("savinilya34@gmail.com")
                .addWebsite("http://vk.com/ilya__savin")
                .addPlayStore("com.lentach")
                .create();

        aboutPage.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        mFrameLayout.addView(aboutPage);



    }
}
