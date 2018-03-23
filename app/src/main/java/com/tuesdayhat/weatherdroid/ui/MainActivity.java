package com.tuesdayhat.weatherdroid.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.tuesdayhat.weatherdroid.R;

import butterknife.ButterKnife;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.weatherButton) Button mWeatherButton;
    @BindView(R.id.aboutButton) Button mAboutButton;
    @BindView(R.id.weatherLocationInput) EditText mWeatherLocationInput;
    @BindView(R.id.appTitle) TextView mAppTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mAboutButton.setOnClickListener(this);
        mWeatherButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v == mAboutButton){
            Intent intent = new Intent(MainActivity.this, AboutPage.class);
            startActivity(intent);
        } else if (v == mWeatherButton){
            String location = mWeatherLocationInput.getText().toString();

            if (location.length() > 0){
                Intent intent = new Intent(MainActivity.this, SourceDetail.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }

        }
    }
}
