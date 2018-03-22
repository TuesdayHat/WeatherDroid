package com.tuesdayhat.weatherdroid.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tuesdayhat.weatherdroid.R;
import com.tuesdayhat.weatherdroid.adapters.WeatherSourceListAdapter;
import com.tuesdayhat.weatherdroid.adapters.WeatherSourcePagerAdapter;
import com.tuesdayhat.weatherdroid.models.WeatherSource;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.BindView;

public class SourceDetails extends AppCompatActivity {
    //TODO: convert this to Cardview/RecyclerView based layout.

//    @BindView(R.id.locationTextView) TextView mLocationTextView;
//    @BindView(R.id.sourceList) ListView mWeatherSourceList;
//
//
//    //eventually Weather Sources will get their own model, as soon as I get a better look at the API data
//    private String[] sources = new String[]{
//            "NOAA National Weather Service", "Accuweather", "WeatherBug", "Wunderground", "AerisWeather",
//            "OpenWeatherMap", "World Weather Online"
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_source_details);
//
//        ButterKnife.bind(this);
//
//        WeatherSourceListAdapter adapter = new WeatherSourceListAdapter(this, android.R.layout.simple_list_item_1, sources);
//        mWeatherSourceList.setAdapter(adapter);
//
//        mWeatherSourceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String weather = ((TextView)view).getText().toString();
//                Toast.makeText(SourceDetails.this, weather, Toast.LENGTH_LONG).show();
//            }
//        });
//        Intent intent = getIntent();
//        String location = intent.getStringExtra("location");
//        mLocationTextView.setText("The Weather In: " + location);
//    }

    @BindView(R.id.viewPager) ViewPager mViewPager;
    private WeatherSourcePagerAdapter adapterViewPager;
    ArrayList<WeatherSource> mWeatherSources = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_details);
        ButterKnife.bind(this);

        mWeatherSources = Parcels.unwrap(getIntent().getParcelableExtra("weatherSources"));
        int startingPosition = getIntent().getIntExtra("position", 0);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }

}
