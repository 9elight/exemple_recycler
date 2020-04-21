package com.delight.weatherapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.delight.weatherapp.data.PreferenceHelper;
import com.delight.weatherapp.ui.splash.SplashActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private boolean firstL;
    @Rule
    ActivityTestRule<SplashActivity> activityTestRule = new ActivityTestRule<>(SplashActivity.class);
    @Before
    public void setUp(){
        PreferenceHelper.init(appContext);

    }


    @Test
    public void isFirsLaunch(){

//        PreferenceHelper.setIsFirsLaunch();
//        firstL = PreferenceHelper.getIsFirsLaunch();
//        assertTrue(firstL);
    }
    @Test
//    public void isNotFirstLaunch(){
//        assertEquals(PreferenceHelper.getIsFirsLaunch(),firstL);
//    }
    @After
    public void after(){
        PreferenceHelper.clearPreference();
        firstL = false;
    }

}
