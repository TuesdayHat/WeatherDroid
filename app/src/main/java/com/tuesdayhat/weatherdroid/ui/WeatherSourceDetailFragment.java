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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tuesdayhat.weatherdroid.Constants;
import com.tuesdayhat.weatherdroid.R;
import com.tuesdayhat.weatherdroid.models.*;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherSourceDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.sourceName) TextView mSourceName;
    @BindView(R.id.currTemp) TextView mCurrTemp;
    @BindView(R.id.currMax) TextView mCurrMax;
    @BindView(R.id.currMin) TextView mCurrMin;
    @BindView(R.id.currHumidity) TextView mHumidity;
    @BindView(R.id.summary) TextView mSummary;
    @BindView(R.id.descriptionTextView) TextView mDescription; //not sure if this is good for anything here
    @BindView(R.id.locationTextView) TextView mLocation;
    @BindView(R.id.saveReportButton) Button mSaveReportButton;

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
        mWeatherSource = Parcels.unwrap(getArguments().getParcelable("weatherSource"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_weather_source_detail, container, false);
        ButterKnife.bind(this, view);

        mSourceName.setText(mWeatherSource.getSourceName());
        mCurrTemp.setText(String.format("%s °",mWeatherSource.getCurrTemp() + "°"));//note that Open Weather Map returns temp in Kelvins
        mHumidity.setText(String.format("Humidity: %s", mWeatherSource.getCurrHumidity() + "%"));
        mCurrMax.setText(String.format("Max: %s °", mWeatherSource.getCurrMax()));
        mCurrMin.setText(String.format("Min: %s °", mWeatherSource.getCurrMin()));
        mSummary.setText(mWeatherSource.getSummary());
        mLocation.setText(mWeatherSource.getLocation());
        mDescription.setText(mWeatherSource.getDescription());


        mSaveReportButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v){
        if(v == mSaveReportButton){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference WeatherSourceRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_SAVED_REPORT)
                    .child(uid);


            DatabaseReference pushRef = WeatherSourceRef.push();
            String pushId = pushRef.getKey();
            mWeatherSource.setPushId(pushId);
            pushRef.setValue(mWeatherSource);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

        }
    }
}
