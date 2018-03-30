package com.tuesdayhat.weatherdroid.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.tuesdayhat.weatherdroid.Constants;
import com.tuesdayhat.weatherdroid.R;
import com.tuesdayhat.weatherdroid.adapters.WeatherSourceListAdapter;
import com.tuesdayhat.weatherdroid.models.WeatherSource;
import com.tuesdayhat.weatherdroid.services.OpenWeatherMapService;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SourceList extends AppCompatActivity {
    public static final String TAG = SourceList.class.getSimpleName();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.locationTextView) TextView mLocationTextView;

    private SharedPreferences mSharedPreferences;
    private String mLastLocation;

    private WeatherSourceListAdapter mAdapter;
    public ArrayList<WeatherSource> sources = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_list);

        ButterKnife.bind(this);

//        Intent intent = getIntent();
//        String location = intent.getStringExtra("location");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mLastLocation = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        Log.d("Shared Pref Location: ", mLastLocation + "");
        if (mLastLocation != null){
            getSources(mLastLocation);
        }

        mLocationTextView.append(" " + mLastLocation);

        getSources(mLastLocation);
    }

    private void getSources(String location){
        final OpenWeatherMapService owmService = new OpenWeatherMapService();
        owmService.currentWeather(location, new Callback(){

            @Override
            public void onFailure(Call call, IOException e){
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response){
                sources = owmService.processResults(response);

                SourceList.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new WeatherSourceListAdapter(getApplicationContext(), sources);
                        mRecyclerView.setAdapter(mAdapter);

                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(SourceList.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        for (WeatherSource source: sources){
                            Log.d(TAG, String.format("-------------SOURCE NAME: %s", source.getSourceName()));
                        }
                    }
                });
            }
        });
    }
}
