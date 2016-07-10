package com.lentach;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.lentach.components.Constants;
import com.lentach.navigator.ActivityNavigator;

public class SplashScreenActivity extends BaseActivity {

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initViewElements();
        setLoading();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLoading();
    }

    private void setLoading() {

        countDownTimer = new CountDownTimer(Constants.LOADING_TIME, Constants.LOADING_INTERVAL) {
            int index=0;

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                countDownTimer.cancel();
                SplashScreenActivity.this.finish();
                ActivityNavigator.startMainActivity(getApplicationContext());

            }

        }.start();
    }

    void initViewElements(){

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        countDownTimer.cancel();

    }
}
