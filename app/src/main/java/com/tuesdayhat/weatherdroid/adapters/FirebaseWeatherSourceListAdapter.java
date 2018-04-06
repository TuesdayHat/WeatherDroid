package com.tuesdayhat.weatherdroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.tuesdayhat.weatherdroid.models.WeatherSource;
import com.tuesdayhat.weatherdroid.ui.SourceDetail;
import com.tuesdayhat.weatherdroid.util.ItemTouchHelperAdapter;
import com.tuesdayhat.weatherdroid.util.OnStartDragListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FirebaseWeatherSourceListAdapter extends FirebaseRecyclerAdapter<WeatherSource, FirebaseWeatherSourceViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    private ChildEventListener mChildEventListener;
    private ArrayList<WeatherSource> mSources = new ArrayList<>();


    public FirebaseWeatherSourceListAdapter (Class<WeatherSource> modelClass, int modelLayout, Class<FirebaseWeatherSourceViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mSources.add(dataSnapshot.getValue(WeatherSource.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseWeatherSourceViewHolder viewHolder, WeatherSource model, int position) {
        viewHolder.bindWeatherSource(model);
        viewHolder.mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mContext, SourceDetail.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("sources", Parcels.wrap(mSources));
                mContext.startActivity(intent);
            }
        });
    }

    private void setIndexInFirebase(){
        for(WeatherSource source : mSources){
            int index = mSources.indexOf(source);
            DatabaseReference ref = getRef(index);
            source.setIndex(Integer.toString(index));
            ref.setValue(source);
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mSources, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mSources.remove(position);
        getRef(position).removeValue();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}
