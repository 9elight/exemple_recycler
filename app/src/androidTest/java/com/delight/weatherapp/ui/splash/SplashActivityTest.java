package com.delight.weatherapp.ui.splash;


import android.util.Log;

import androidx.test.espresso.intent.Intents;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.delight.weatherapp.data.PreferenceHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);
    @Before
    public void before(){
        Intents.init();

    }
    @Test
    public void splashActivityTest() {
        PreferenceHelper.clearPreference();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Tag", "splashActivityTest: ");
    }
    @Test
    public void splashActivityTest2() {
        PreferenceHelper.setIsFirsLaunch();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Tag", "splashActivityTest: ");
    }
    @After
    public void after(){
        PreferenceHelper.clearPreference();
        Intents.release();
    }

}
