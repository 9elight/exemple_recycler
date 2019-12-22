package com.delight.weatherapp.data;

import com.delight.weatherapp.data.entity.CurrentWeather;
import com.delight.weatherapp.data.entity.ForCastEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.delight.weatherapp.data.ApiEndPoints.CURRENT_WEATHER;
import static com.delight.weatherapp.data.ApiEndPoints.FORECAST_WEATHER;

public interface WeatherService {

    @GET(CURRENT_WEATHER)
    Call<CurrentWeather> currentWeather(@Query("q") String city,
                                        @Query("units") String format,
                                        @Query("appid") String key);
    @GET(FORECAST_WEATHER)
    Call<ForCastEntity> forCastWeather(@Query("q") String city,
                                       @Query("units") String format,
                                       @Query("appid") String key);

    @GET(CURRENT_WEATHER)
    Call<CurrentWeather> coordCurrentWeather(@Query("lat") double lat,
                                             @Query("lon") double lon,
                                             @Query("units") String format,
                                             @Query("appid") String key);
    @GET(FORECAST_WEATHER)
    Call<ForCastEntity> coordForCastWeather(@Query("lat") double lat,
                                            @Query("lon") double lon,
                                       @Query("units") String format,
                                       @Query("appid") String key);

}
