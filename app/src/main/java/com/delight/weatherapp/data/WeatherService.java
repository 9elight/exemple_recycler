package com.delight.weatherapp.data;

import com.delight.weatherapp.data.entity.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather")
    Call<CurrentWeather> currentWeather(@Query("q") String city,
                                        @Query("units") String format,
                                        @Query("appid") String key);
}
