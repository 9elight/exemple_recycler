package com.delight.weatherapp.ui.onBoard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.delight.weatherapp.R;
import com.delight.weatherapp.data.OnBoardData;
import com.delight.weatherapp.data.PreferenceHelper;
import com.delight.weatherapp.ui.main.MainActivity;
import com.google.android.material.tabs.TabLayout;

import java.security.PrivateKey;
import java.util.ArrayList;

public class OnBoardActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    private Button nextButton;
    private Button prevButton;
    private Button startButton;
    private int mCurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        initViews();
        initListeners();
        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, OnBoardActivity.class));
    }

    //Dots Indicator
    public void addDotsIndicator(int position) {
        mDots = new TextView[4];
        mDotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorBlue));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;
            if (position == 0) {
                nextButton.setEnabled(true);
                prevButton.setEnabled(false);
                prevButton.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.INVISIBLE);
            } else if (position == mDots.length - 1) {
                nextButton.setEnabled(true);
                prevButton.setEnabled(true);
                prevButton.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.INVISIBLE);
            } else {
                nextButton.setEnabled(true);
                prevButton.setEnabled(true);
                prevButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.skip:
                MainActivity.start(this);
                finish();
                break;
        }
        return true;
    }

    //findViewById
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_onboard, menu);
        return true;
    }

    private void initViews() {
        viewPager = findViewById(R.id.viewPager);
        myPagerAdapter = new MyPagerAdapter(getList());
        viewPager.setAdapter(myPagerAdapter);
        toolbar = findViewById(R.id.appBar);
        mDotLayout = findViewById(R.id.dots_selector);
        nextButton = findViewById(R.id.viewPager_next_btn);
        prevButton = findViewById(R.id.viewPager_prev_button);
        startButton = findViewById(R.id.start_btn);
        setSupportActionBar(toolbar);
    }

    //setOnClick(this)

    private void initListeners() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(mCurrentPage + 1);
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.start(OnBoardActivity.this);
                finish();
            }
        });
    }
    private ArrayList<OnBoardData> getList(){
        ArrayList<OnBoardData> list = new ArrayList<>();
        list.add(new OnBoardData (getResources().getString(R.string.OB_firstPage),R.drawable.sun));
        list.add(new OnBoardData (getResources().getString(R.string.OB_secondPage),R.drawable.rain));
        list.add(new OnBoardData (getResources().getString(R.string.OB_thirdPage),R.drawable.snow));
        list.add(new OnBoardData (getResources().getString(R.string.OB_FourthPage),R.drawable.storm));
        return list;
    }

}
