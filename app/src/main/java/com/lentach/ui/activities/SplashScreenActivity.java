package com.lentach.ui.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.lentach.R;
import com.lentach.ui.components.Constants;
import com.lentach.router.ActivityRouter;

public class SplashScreenActivity extends AppCompatActivity {

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
                ActivityRouter.startMainActivity(getApplicationContext());

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
