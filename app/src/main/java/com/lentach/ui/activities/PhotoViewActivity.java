package com.lentach.ui.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lentach.R;
import com.lentach.ui.components.Constants;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoView;

public class PhotoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        PhotoView photoView = (PhotoView)findViewById(R.id.iv_photo);


        String photo = getIntent().getStringExtra("Photo");
        int comment = getIntent().getIntExtra("Comment",0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(comment == 0)
            photoView.setTransitionName(Constants.IMAGE_TRANSITION);
            else photoView.setTransitionName(Constants.IMAGE_COMMENT_TRANSITION);
        }

        Picasso.with(this).load(photo).placeholder(R.drawable.lentach_placeholder).error(R.drawable.lentach_placeholder).into(photoView);


    }



}
