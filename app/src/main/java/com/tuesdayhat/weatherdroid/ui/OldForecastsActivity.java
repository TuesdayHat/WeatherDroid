package com.tuesdayhat.weatherdroid.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.tuesdayhat.weatherdroid.Constants;
import com.tuesdayhat.weatherdroid.R;
import com.tuesdayhat.weatherdroid.adapters.FirebaseWeatherSourceListAdapter;
import com.tuesdayhat.weatherdroid.adapters.FirebaseWeatherSourceViewHolder;
import com.tuesdayhat.weatherdroid.models.WeatherSource;
import com.tuesdayhat.weatherdroid.util.OnStartDragListener;
import com.tuesdayhat.weatherdroid.util.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OldForecastsActivity extends AppCompatActivity implements OnStartDragListener {

    private DatabaseReference mOldForecastsReference;
    private FirebaseWeatherSourceListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.locationTextView) TextView mLocationTextView;

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

        mLocationTextView.append("Saved Weather Reports");

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_SAVED_REPORT)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mOldForecastsReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_SAVED_REPORT)
                .child(uid);
        mFirebaseAdapter = new FirebaseWeatherSourceListAdapter(WeatherSource.class,
                R.layout.source_list_item, FirebaseWeatherSourceViewHolder.class,
                query, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
