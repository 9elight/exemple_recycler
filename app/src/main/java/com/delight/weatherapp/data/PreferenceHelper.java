package com.delight.weatherapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceHelper {

    private static SharedPreferences preferences;
    private static final String IS_FIRST_LAUNCH = "isFirsLaunch";
    private static final String NAME_PREFS = "NAME_PREFS";


    public static void init(Context context){
        preferences = context.getSharedPreferences(NAME_PREFS, MODE_PRIVATE);
    }

    public static SharedPreferences getPreferences(){
        return preferences;
    }
    public static void setIsFirsLaunch(){
        preferences.edit().putBoolean(IS_FIRST_LAUNCH, true).apply();
    }
    public static boolean getIsFirsLaunch(){
        return preferences.getBoolean(IS_FIRST_LAUNCH, false);
    }


}
