package com.delight.weatherapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.delight.weatherapp.R;
import com.delight.weatherapp.base.BaseActivity;
import com.delight.weatherapp.data.RetrofitBuilder;
import com.delight.weatherapp.data.entity.CurrentWeather;
import com.delight.weatherapp.data.entity.ForCastEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.delight.weatherapp.BuildConfig.API_KEY;
import static com.delight.weatherapp.utils.DateParser.parseDateToTime;

public class MainActivity extends BaseActivity {
    @BindView(R.id.text_place) TextView city;
    @BindView(R.id.temp) TextView temp;
    @BindView(R.id.temp_max) TextView maxTemp;
    @BindView(R.id.temp_min) TextView minTemp;
    @BindView(R.id.text_wind_condition) TextView windSpeed;
    @BindView(R.id.text_pressure_condition) TextView pressure;
    @BindView(R.id.text_humidity_condition) TextView humidity;
    @BindView(R.id.text_cloudiness_condition) TextView cloudiness;
    @BindView(R.id.text_sunrise_time) TextView sunrise;
    @BindView(R.id.text_sunset_time) TextView sunset;
    @BindView(R.id.day) TextView day;
    @BindView(R.id.month) TextView month;
    @BindView(R.id.year) TextView year;
    @BindView(R.id.text_weather_condition) TextView weatherCondition;
    @BindView(R.id.ic_weather) ImageView weatherImg;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWeather();
        getWeatherForCast();
    }

    public static void start(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private void getWeather(){
        RetrofitBuilder.getService()
                .currentWeather("Bishkek","metric",API_KEY)
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null){
                            setWeather(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }
    private void getWeatherForCast(){
        RetrofitBuilder.getService()
                .forCastWeather("Bishkek","metric",API_KEY)
                .enqueue(new Callback<ForCastEntity>() {
                    @Override
                    public void onResponse(Call<ForCastEntity> call, Response<ForCastEntity> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            rv_builder(response.body().getList());
                        }
                    }

                    @Override
                    public void onFailure(Call<ForCastEntity> call, Throwable t) {

                    }
                });
    }

    private void setWeather(CurrentWeather weather){
        city.setText(weather.getName());
        temp.setText(weather.getMain().getTemp().toString());
        maxTemp.setText(weather.getMain().getTempMax().toString());
        minTemp.setText(weather.getMain().getTempMin().toString());
        windSpeed.setText(weather.getWind().getSpeed().toString());
        pressure.setText(weather.getMain().getPressure().toString());
        humidity.setText(weather.getMain().getHumidity().toString());
        cloudiness.setText(weather.getClouds().getAll().toString());
        sunrise.setText(parseDateToTime(weather.getSys().getSunrise()));
        sunset.setText(parseDateToTime(weather.getSys().getSunset()));
        weatherCondition.setText(weather.getWeather().get(0).getDescription());
        day.setText(new SimpleDateFormat("dd").format(new Date()));
        month.setText(new SimpleDateFormat("MMMM").format(new Date()));
        year.setText(new SimpleDateFormat("yyyy").format(new Date()));
        Glide.with(getApplicationContext())
                .load("http://openweathermap.org/img/wn/"
                        + weather.getWeather().get(0).getIcon()
                        + "@2x.png").into(weatherImg);
    }
    private void rv_builder(List<CurrentWeather> list){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RV_Adapter(list));
    }

}
