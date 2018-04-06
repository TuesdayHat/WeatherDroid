package com.tuesdayhat.weatherdroid.adapters;

import android.content.Context;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.tuesdayhat.weatherdroid.models.WeatherSource;
import com.tuesdayhat.weatherdroid.util.ItemTouchHelperAdapter;
import com.tuesdayhat.weatherdroid.util.OnStartDragListener;

public class FirebaseWeatherSourceListAdapter extends FirebaseRecyclerAdapter<WeatherSource, FirebaseWeatherSourceViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseWeatherSourceListAdapter (Class<WeatherSource> modelClass, int modelLayout, Class<FirebaseWeatherSourceViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context){
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(FirebaseWeatherSourceViewHolder viewHolder, WeatherSource model, int position) {
        viewHolder.bindWeatherSource(model);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
