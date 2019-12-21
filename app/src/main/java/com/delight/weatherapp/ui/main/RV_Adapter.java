package com.delight.weatherapp.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delight.weatherapp.R;
import com.delight.weatherapp.data.entity.CurrentWeather;


import java.util.ArrayList;
import java.util.List;

public class RV_Adapter extends RecyclerView.Adapter<RV_view_holder> {
    private List<CurrentWeather> list;

    public RV_Adapter() {


    }

    void updateWeather(List<CurrentWeather> list){
        this.list = list;
    }

    @NonNull
    @Override
    public RV_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_view_holder,parent,false);
        RV_view_holder vh = new RV_view_holder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RV_view_holder holder, int position) {
        holder.onBind(list.get(position).getDateTimeForCast(),
                list.get(position).getMain().getTempMax().toString()
                ,list.get(position).getMain().getTempMin().toString()
                ,list.get(position).getWeather().get(0).getIcon(),position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}