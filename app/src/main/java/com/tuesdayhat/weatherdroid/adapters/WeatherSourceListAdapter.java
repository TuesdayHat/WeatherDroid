package com.tuesdayhat.weatherdroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tuesdayhat.weatherdroid.R;
import com.tuesdayhat.weatherdroid.models.WeatherSource;
import com.tuesdayhat.weatherdroid.ui.SourceDetail;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherSourceListAdapter extends RecyclerView.Adapter<WeatherSourceListAdapter.WeatherSourceViewHolder> {

    private ArrayList<WeatherSource> mWeatherModels = new ArrayList<>();
    private Context mContext;

    public WeatherSourceListAdapter(Context mContext, ArrayList<WeatherSource> mWeatherModels){
        this.mContext = mContext;
        this.mWeatherModels = mWeatherModels;
    }

    @Override
    public WeatherSourceListAdapter.WeatherSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_list_item, parent, false);
        WeatherSourceViewHolder viewHolder = new WeatherSourceViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherSourceListAdapter.WeatherSourceViewHolder holder, int position){
        holder.bindSource(mWeatherModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mWeatherModels.size();
    }

    public class WeatherSourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.currTemp) TextView mCurrTemp;
        @BindView(R.id.currMax) TextView mCurrMax;
        @BindView(R.id.currMin) TextView mCurrMin;
        @BindView(R.id.currHumidity) TextView mCurrHumidity;
        @BindView(R.id.summary) TextView mSummary;
        @BindView(R.id.sourceName) TextView mSourceName;


        private Context mContext;

        public WeatherSourceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext=itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, SourceDetail.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("weatherSources", Parcels.wrap(mWeatherModels));
            mContext.startActivity(intent);
        }

        public void bindSource(WeatherSource source){
            //TODO: work out what needs to go onto every member of the list of weather sources.
            mSourceName.setText(source.getSourceName());
            mCurrTemp.setText(String.format("%s °", source.getCurrTemp()));
            mCurrMax.setText(String.format("%s °", source.getCurrMax()));
            mCurrMin.setText(String.format("%s °", source.getCurrMin()));
            mCurrHumidity.setText(String.format("%s", source.getCurrHumidity()));
            mSummary.setText(source.getSummary());

        }
    }
}
