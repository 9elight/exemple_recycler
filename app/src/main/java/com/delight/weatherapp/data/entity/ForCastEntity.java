package com.delight.weatherapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForCastEntity {
    @SerializedName("list")
    private List<CurrentWeather> list;

    public List<CurrentWeather> getList() {
        return list;
    }

    public void setList(List<CurrentWeather> list) {
        this.list = list;
    }
}
