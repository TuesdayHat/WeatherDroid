package com.tuesdayhat.weatherdroid.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tuesdayhat.weatherdroid.Constants;
import com.tuesdayhat.weatherdroid.R;
import com.tuesdayhat.weatherdroid.models.WeatherSource;
import com.tuesdayhat.weatherdroid.ui.SourceDetail;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseWeatherSourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseWeatherSourceViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindWeatherSource(WeatherSource source){
        TextView sourceNameTextView = (TextView) mView.findViewById(R.id.sourceName);
        TextView currTempTextView = (TextView) mView.findViewById(R.id.currTemp);
        TextView currMaxTextView = (TextView) mView.findViewById(R.id.currMax);
        TextView currMinTextView = (TextView) mView.findViewById(R.id.currMin);
        TextView currHumidityTextView = (TextView) mView.findViewById(R.id.currHumidity);
        TextView summaryTextView = (TextView) mView.findViewById(R.id.summary);

        sourceNameTextView.setText(source.getSourceName());
        currTempTextView.setText(String.format("%s °", source.getCurrTemp()));
        currMaxTextView.setText(String.format("%s °", source.getCurrMax()));
        currMinTextView.setText(String.format("%s °", source.getCurrMin()));
        currHumidityTextView.setText(String.format("%s", source.getCurrHumidity()));
        summaryTextView.setText(source.getSummary());
    }

    @Override
    public void onClick(View view){
        final ArrayList<WeatherSource> sources = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SOURCE);
        ref.addListenerForSingleValueEvent(new ValueEventListener(){
            
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    sources.add(snapshot.getValue(WeatherSource.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, SourceDetail.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("sources", Parcels.wrap(sources));

                mContext.startActivity(intent);
            }

           @Override
           public void onCancelled(DatabaseError databaseError){}
        });
    }
}
