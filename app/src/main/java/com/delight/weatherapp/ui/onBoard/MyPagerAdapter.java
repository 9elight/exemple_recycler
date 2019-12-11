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


public class MyPagerAdapter extends androidx.viewpager.widget.PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private int[] img = {R.drawable.sun,R.drawable.rain,R.drawable.snow,R.drawable.storm};
    private String[] text = {"SUN","RAIN","SNOW","STORM"};

    public MyPagerAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_pager_layout,container,false);
        ImageView imageView =  view.findViewById(R.id.viewPager_img);
        TextView textView = view.findViewById(R.id.viewPager_text);

        imageView.setImageResource(img[position]);
        textView.setText(text[position]);
        container.addView(view);
        return view;



    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);

    }
}
