package com.tuesdayhat.weatherdroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    @BindView(R.id.sourcesRecyclerView) RecyclerView mRecyclerView;

    private WeatherSourceListAdapter mAdapter;

    public ArrayList<WeatherSource> sources = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_list);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
//        Log.d("----LOCATION EXTRA: ", location);

        getSources(location);
    }

    private void getSources(String location){
        final OpenWeatherMapService owmService = new OpenWeatherMapService();
//        Log.d("----LOCATION ", location);
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

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SourceList.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
