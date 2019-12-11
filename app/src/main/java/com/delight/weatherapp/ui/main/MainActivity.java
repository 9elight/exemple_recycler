package com.delight.weatherapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.delight.weatherapp.R;
import com.delight.weatherapp.ui.onBoard.OnBoardActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void start(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
