package com.tuesdayhat.weatherdroid.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tuesdayhat.weatherdroid.R;
import com.tuesdayhat.weatherdroid.adapters.WeatherSourcePagerAdapter;
import com.tuesdayhat.weatherdroid.models.WeatherSource;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.BindView;

public class SourceDetail extends AppCompatActivity {

    private WeatherSourcePagerAdapter adapterViewPager;
    ArrayList<WeatherSource> mWeatherSources = new ArrayList<>();

    @BindView(R.id.viewPager) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_detail);
        ButterKnife.bind(this);

        mWeatherSources = Parcels.unwrap(getIntent().getParcelableExtra("weatherSources"));
        Log.d("------LIST OF SOURCES: ", String.format("%s", mWeatherSources));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new WeatherSourcePagerAdapter(getSupportFragmentManager(), mWeatherSources);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }

}
