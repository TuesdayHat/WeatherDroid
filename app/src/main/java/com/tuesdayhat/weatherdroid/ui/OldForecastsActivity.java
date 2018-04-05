package com.tuesdayhat.weatherdroid.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tuesdayhat.weatherdroid.Constants;
import com.tuesdayhat.weatherdroid.R;
import com.tuesdayhat.weatherdroid.adapters.FirebaseWeatherSourceViewHolder;
import com.tuesdayhat.weatherdroid.models.WeatherSource;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OldForecastsActivity extends AppCompatActivity {

    private DatabaseReference mOldForecastsReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_list);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mOldForecastsReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_SAVED_REPORT)
                .child(uid);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<WeatherSource, FirebaseWeatherSourceViewHolder>
                (WeatherSource.class, R.layout.source_list_item, FirebaseWeatherSourceViewHolder.class, mOldForecastsReference) {
            @Override
            protected void populateViewHolder(FirebaseWeatherSourceViewHolder viewHolder, WeatherSource model, int position) {
                viewHolder.bindWeatherSource(model);
            }
        };

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
