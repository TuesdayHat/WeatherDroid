package com.tuesdayhat.weatherdroid.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuesdayhat.weatherdroid.R;
import com.tuesdayhat.weatherdroid.models.*;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherSourceDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.sourceName) TextView mSourceName;
    @BindView(R.id.currTemp) TextView mCurrTemp;
    @BindView(R.id.currMax) TextView mCurrMax;
    @BindView(R.id.currHumidity) TextView mHumidity;
    @BindView(R.id.summary) TextView mSummary;
    @BindView(R.id.description) TextView mDescription;
    @BindView(R.id.location) TextView mLocation;

    private WeatherSource mWeatherSource;

    public static WeatherSourceDetailFragment newInstance(WeatherSource weatherSource){
        WeatherSourceDetailFragment weatherSourceDetailFragment = new WeatherSourceDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("weatherSource", Parcels.wrap(weatherSource));
        weatherSourceDetailFragment.setArguments(args);
        return weatherSourceDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherSource = Parcels.unwrap(getArguments().getParcelable("weatherSource"))
    }


}