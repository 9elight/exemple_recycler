package com.delight.weatherapp.data;

public class OnBoardData {
    private String title;
    private int image;

    public OnBoardData(String title,int image){
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
