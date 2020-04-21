package com.delight.weatherapp.ui.main;

import android.widget.Toast;

import com.delight.weatherapp.data.RetrofitBuilder;
import com.delight.weatherapp.data.entity.CurrentWeather;
import com.delight.weatherapp.data.entity.ForCastEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.delight.weatherapp.BuildConfig.API_KEY;
import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;

    @Override
    public void bind(MainContract.View view) {
        mView = view;
    }

    private boolean isViewAttached() {
        return mView != null;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    @Override
    public void getWeatherForCoord(double lat, double lng) {
        RetrofitBuilder.getService().coordCurrentWeather(lat, lng, "metric", API_KEY)
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (isViewAttached()) mView.setWeather(response.body());
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        if (isViewAttached()) mView.toast(t.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void getForeCastWeatherForCoord(double lat, double lon) {
        RetrofitBuilder.getService().coordForCastWeather(lat, lon, "metric", API_KEY)
                .enqueue(new Callback<ForCastEntity>() {
                    @Override
                    public void onResponse(Call<ForCastEntity> call, Response<ForCastEntity> response) {
                        mView.rv_builder(response.body().getList());
                    }

                    @Override
                    public void onFailure(Call<ForCastEntity> call, Throwable t) {
                        mView.toast(t.getLocalizedMessage());
                    }
                });
    }
}
