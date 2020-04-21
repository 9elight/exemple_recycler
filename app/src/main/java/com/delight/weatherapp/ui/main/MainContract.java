package com.delight.weatherapp.ui.main;

import com.delight.weatherapp.Lifecycles;
import com.delight.weatherapp.data.entity.CurrentWeather;

import java.util.List;

public interface MainContract {
    interface View{
        void setWeather(CurrentWeather weather);
        void rv_builder(List<CurrentWeather> list);
        void toast(String msg);
    }
    interface Presenter extends Lifecycles<View> {
        void getWeatherForCoord(double lat, double lng);
        void getForeCastWeatherForCoord(double lat, double lon);
    }
}
