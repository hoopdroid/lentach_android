package com.lentach;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lentach.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

/**
 * Created by Илья on 21.07.2016.
 */

public class UserActivity extends BaseActivity {


    @Bind(R.id.userImage)
    ImageView userImage;
    @Bind(R.id.userName)
    TextView userName;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);


        SharedPreferences sharedPreferences = getSharedPreferences("Default", MODE_PRIVATE);


        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        userName.setText(getIntent().getStringExtra("Username"));
        Picasso.with(this).load(sharedPreferences.getString("avatar_image", "")).placeholder(R.drawable.lentach_placeholder).
                error(R.drawable.lentach_placeholder).into(userImage);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // overridePendingTransition(R.anim.down_to_top, R.anim.up_from_bottom);
        ;
    }
}
