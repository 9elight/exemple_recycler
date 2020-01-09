package com.delight.weatherapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.delight.weatherapp.data.PreferenceHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Mock
    Context context;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        PreferenceHelper.init(context);
    }
    ///true
    @Test
    public void isFirsLaunch(){
        PreferenceHelper preferenceHelper = new PreferenceHelper();
        when(preferenceHelper.getIsFirsLaunch()).thenReturn(true);
        assertEquals(PreferenceHelper.getIsFirsLaunch(),true);
    }


}