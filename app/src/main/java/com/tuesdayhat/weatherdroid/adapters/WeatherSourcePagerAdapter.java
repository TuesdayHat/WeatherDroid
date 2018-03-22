package com.tuesdayhat.weatherdroid.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tuesdayhat.weatherdroid.models.WeatherSource;

import java.util.ArrayList;

public class WeatherSourcePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<WeatherSource> mSources;

    public WeatherSourcePagerAdapter(FragmentManager fm, ArrayList<WeatherSource> sources){
        super(fm);
        mSources = sources;
    }

    @Override
    public Fragment getItem(int position){
        return WeatherSourceDetailFragment.newInstance(mSources.get(position));
    }

    @Override
    public int getCount() {return mSources.size();}

    @Override
    public CharSequence getPageTitle(int position){return mSources.get(position).getSourceName();}
}
