package com.delight.weatherapp.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.delight.weatherapp.R;


import java.text.ParseException;

import static com.delight.weatherapp.utils.DateParser.forcastDate;

public class RV_view_holder extends RecyclerView.ViewHolder {
    private TextView date,maxTemperature,minTemperature;
    private ImageView weatherIcon;
    public RV_view_holder(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.vh_temp);
        maxTemperature = itemView.findViewById(R.id.vh_MaxTemp);
        minTemperature = itemView.findViewById(R.id.vh_MinTemp);
        weatherIcon = itemView.findViewById(R.id.vh_image);

    }
    public void onBind(String date,String maxTemp,String minTemp,String img,int position){

        try {
            this.date.setText(forcastDate(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        maxTemperature.setText(maxTemp);
        minTemperature.setText(minTemp);
        Glide.with(itemView).load("http://openweathermap.org/img/wn/" + img + "@2x.png").into(weatherIcon);
    }


}
