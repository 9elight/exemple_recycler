package com.delight.weatherapp.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.delight.weatherapp.R;
import com.delight.weatherapp.data.PreferenceHelper;
import com.delight.weatherapp.ui.main.MainActivity;
import com.delight.weatherapp.ui.onBoard.OnBoardActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                selectActivity();
            }
        }, 1_000);


    }

    private void selectActivity() {
        if (PreferenceHelper.getIsFirsLaunch()) {
            MainActivity.start(this);
        } else {
            PreferenceHelper.setIsFirsLaunch();
            OnBoardActivity.start(this);
        }
        finish();
    }
}
