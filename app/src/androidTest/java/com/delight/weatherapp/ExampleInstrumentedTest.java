package com.delight.weatherapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.delight.weatherapp.data.PreferenceHelper;

import org.junit.After;
import org.junit.Before;
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

    @Before
    public void setUp(){
        PreferenceHelper.init(appContext);
    }
    @Test
    public void isFirsLaunch(){
        PreferenceHelper.setIsFirsLaunch();
        firstL = PreferenceHelper.getIsFirsLaunch();
        assertTrue(firstL);
    }
    @Test
    public void isNotFirstLaunch(){
        assertEquals(PreferenceHelper.getIsFirsLaunch(),firstL);
    }
    @After
    public void setNull(){
        firstL = false;
    }

}
