package com.delight.weatherapp.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.delight.weatherapp.R;
import com.delight.weatherapp.base.BaseActivity;
import com.delight.weatherapp.data.entity.CurrentWeather;
import com.delight.weatherapp.ui.map.MapActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import static com.delight.weatherapp.utils.DateParser.parseDateToTime;

public class MainActivity extends BaseActivity implements
MainContract.View{
    @BindView(R.id.text_place)
    TextView city;
    @BindView(R.id.temp)
    TextView temp;
    @BindView(R.id.temp_max)
    TextView maxTemp;
    @BindView(R.id.temp_min)
    TextView minTemp;
    @BindView(R.id.text_wind_condition)
    TextView windSpeed;
    @BindView(R.id.text_pressure_condition)
    TextView pressure;
    @BindView(R.id.text_humidity_condition)
    TextView humidity;
    @BindView(R.id.text_cloudiness_condition)
    TextView cloudiness;
    @BindView(R.id.text_sunrise_time)
    TextView sunrise;
    @BindView(R.id.text_sunset_time)
    TextView sunset;
    @BindView(R.id.day)
    TextView day;
    @BindView(R.id.month)
    TextView month;
    @BindView(R.id.year)
    TextView year;
    @BindView(R.id.text_weather_condition)
    TextView weatherCondition;
    @BindView(R.id.ic_weather)
    ImageView weatherImg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ic_place)
    ImageView ic_map;

    private MainContract.Presenter mPresenter;
    private double lat;
    private double lng;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainPresenter();
        mPresenter.bind(this);
        callPermissions();
        getCurrentCoordinate();
        Toast.makeText(this,lat + " " + lng,Toast.LENGTH_LONG).show();


    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }


    public void setWeather(CurrentWeather weather) {
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

    @Override
    public void toast(String msg) {
        toast(msg);
    }

    public void rv_builder(List<CurrentWeather> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        RV_Adapter adapter = new RV_Adapter();
        recyclerView.setAdapter(adapter);
        adapter.updateWeather(list);
    }


    public void openMap(View view) {
        startActivityForResult(new Intent(this, MapActivity.class), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100 && data != null) {
            lat = data.getDoubleExtra("lat", 0);
            lng = data.getDoubleExtra("lng", 0);
            mPresenter.getWeatherForCoord(lat, lng);
            mPresenter.getForeCastWeatherForCoord(lat, lng);
        }
    }
    private void getCurrentCoordinate() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PermissionChecker.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PermissionChecker.PERMISSION_GRANTED) {
            FusedLocationProviderClient fusedLocationProvider = new FusedLocationProviderClient(this);
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//            locationRequest.setFastestInterval(2000);
//            locationRequest.setInterval(4000);
            fusedLocationProvider.requestLocationUpdates(locationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {

                    super.onLocationResult(locationResult);
                    Log.e("------", "lat: " + locationResult.getLastLocation().getLatitude() +
                            " lon: " + locationResult.getLastLocation().getLongitude() + "  ");
                    double latitude = locationResult.getLastLocation().getLatitude();
                    double longitude = locationResult.getLastLocation().getLongitude();
                    mPresenter.getWeatherForCoord(latitude,longitude);
                    mPresenter.getForeCastWeatherForCoord(latitude,longitude);
                }
            }, getMainLooper());

        }
    }
    public void callPermissions(){
        Permissions.check(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION}, "Нужен доступ к местоположению",
                new Permissions.Options().setSettingsDialogTitle("Внимание!!!").setRationaleDialogTitle("Доступ к местоположению"),
                new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        getCurrentCoordinate();
                    }

                    @Override
                    public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                        super.onDenied(context, deniedPermissions);
                        callPermissions();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
