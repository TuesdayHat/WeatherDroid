package com.tuesdayhat.weatherdroid.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tuesdayhat.weatherdroid.R;
import com.tuesdayhat.weatherdroid.adapters.WeatherSourcePagerAdapter;
import com.tuesdayhat.weatherdroid.models.WeatherSource;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.BindView;

public class SourceDetail extends AppCompatActivity {

    @BindView(R.id.viewPager) ViewPager mViewPager;

    private WeatherSourcePagerAdapter adapterViewPager;
    ArrayList<WeatherSource> mWeatherSources = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_list);
        ButterKnife.bind(this);

        mWeatherSources = Parcels.unwrap(getIntent().getParcelableExtra("weatherSources"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new WeatherSourcePagerAdapter(getSupportFragmentManager(), mWeatherSources);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }

}
