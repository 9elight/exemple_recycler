package com.delight.weatherapp.ui.onBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.delight.weatherapp.R;
import com.delight.weatherapp.data.OnBoardData;

import java.util.ArrayList;


public class MyPagerAdapter extends androidx.viewpager.widget.PagerAdapter {

    private LayoutInflater inflater;
    private ArrayList<OnBoardData> data;

    public MyPagerAdapter(ArrayList<OnBoardData> data){
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.view_pager_layout,container,false);
        ImageView imageView =  view.findViewById(R.id.viewPager_img);
        TextView textView = view.findViewById(R.id.viewPager_text);

        imageView.setImageResource(data.get(position).getImage());
        textView.setText(data.get(position).getTitle());
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
