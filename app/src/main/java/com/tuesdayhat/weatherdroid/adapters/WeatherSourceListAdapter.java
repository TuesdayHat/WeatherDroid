package com.tuesdayhat.weatherdroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

public class WeatherSourceListAdapter extends RecyclerView.Adapter<WeatherSourceListAdapter.WeatherSourceListAdapter.WeatherSourceViewHolder> {
    private Context mContext;
    private String[] mWeatherModels;

    public WeatherSourceListAdapter(Context mContext, int resource, String[] mWeatherModels){
        super(mContext, resource);
        this.mContext = mContext;
        this.mWeatherModels = mWeatherModels;
    }

    @Override
    public Object getItem(int position){
        String model = mWeatherModels[position];
        return String.format("Source: %s", model.toString());
    }

    @Override
    public int getCount() {
        return mWeatherModels.length;
    }
}
