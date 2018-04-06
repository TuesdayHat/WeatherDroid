package com.tuesdayhat.weatherdroid.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tuesdayhat.weatherdroid.Constants;
import com.tuesdayhat.weatherdroid.R;

import java.sql.DatabaseMetaData;

import butterknife.ButterKnife;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference mLocationHistoryReference;
    private ValueEventListener mLocationHIstoryReferenceListener;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @BindView(R.id.weatherButton) Button mWeatherButton;
    @BindView(R.id.aboutButton) Button mAboutButton;
    @BindView(R.id.weatherLocationInput) EditText mWeatherLocationInput;
    @BindView(R.id.appTitle) TextView mAppTitle;
    @BindView(R.id.OldReportsButton) Button mOldReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLocationHistoryReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();


//        mWeatherLocationInput.setText(Constants.PREFERENCES_LOCATION_KEY, null);

        mAboutButton.setOnClickListener(this);
        mWeatherButton.setOnClickListener(this);
        mOldReports.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v == mAboutButton){
            Intent intent = new Intent(MainActivity.this, AboutPage.class);
            startActivity(intent);
        } else if (v == mWeatherButton){
            String location = mWeatherLocationInput.getText().toString();

            if (mSharedPreferences != null){
                if(location.length() > 0){
                    addToSharedPreferences(location);
                }
                Intent intent = new Intent(MainActivity.this, SourceList.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }

        } else if (v == mOldReports){
            Intent intent = new Intent(MainActivity.this, OldForecastsActivity.class);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String location){
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
