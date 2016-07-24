package com.lentach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import butterknife.Bind;

public class VideoViewActivity extends BaseActivity{

    @Bind(R.id.videoView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);

        String url = getIntent().getStringExtra("url");

        //https://rutube.ru/play/embed/8744679?__ref=vk.api

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);



    }
}
