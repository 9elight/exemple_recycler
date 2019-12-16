package com.delight.weatherapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.delight.weatherapp.R;
import com.delight.weatherapp.data.RetrifitBuilder;
import com.delight.weatherapp.data.entity.CurrentWeather;
import com.delight.weatherapp.ui.onBoard.OnBoardActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView city, temp , maxTemp, minTemp,windSpeed,pressure
            ,humidity,cloudiness,sunrise,sunset,day,month,year;
    private ImageView weatherImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getWeather();
    }

    public static void start(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private void getWeather(){
        RetrifitBuilder.getService()
                .currentWeather("Bishkek","metric",getResources().getString(R.string.weatherKey))
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null){
                            setWeather(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void initViews(){
        city = findViewById(R.id.text_place);
        temp = findViewById(R.id.temp);
        maxTemp = findViewById(R.id.temp_max);
        minTemp = findViewById(R.id.temp_min);
        windSpeed = findViewById(R.id.text_wind_condition);
        pressure = findViewById(R.id.text_pressure_condition);
        humidity = findViewById(R.id.text_humidity_condition);
        cloudiness = findViewById(R.id.text_cloudiness_condition);
        weatherImg = findViewById(R.id.ic_weather);
        sunrise = findViewById(R.id.text_sunrise_condition);
        sunset = findViewById(R.id.text_sunset_condition);
        day = findViewById(R.id.day);


    }

    private void setWeather(Response<CurrentWeather> response){
        city.setText(response.body().getName());
        temp.setText(response.body().getMain().getTemp().toString());
        maxTemp.setText(response.body().getMain().getTempMax().toString());
        minTemp.setText(response.body().getMain().getTempMin().toString());
        windSpeed.setText(response.body().getWind().getSpeed().toString());
        pressure.setText(response.body().getMain().getPressure().toString());
        humidity.setText(response.body().getMain().getHumidity().toString());
        cloudiness.setText(response.body().getClouds().getAll().toString());
        sunrise.setText(response.body().getSys().getSunrise().toString());
        sunset.setText(response.body().getSys().getSunset().toString());
        Glide.with(getApplicationContext())
                .load("http://openweathermap.org/img/wn/"
                        + response.body().getWeather().get(0).getIcon()
                        + "@2x.png").into(weatherImg);
        day.setText(new SimpleDateFormat("dd MMMM yyyy").format(new Date()));

    }
}
