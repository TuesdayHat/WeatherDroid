package com.tuesdayhat.weatherdroid.models;

import org.parceler.Parcel;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Parcel
public class WeatherSource {
    private String sourceName;
    private Double currTemp; //NOTE: Open Weather Map api responds in Kelvins
    private Double currMax;
    private Double currMin;
    private Double currHumidity;
    private String summary;
    private String description;
    private String location;
    private String timestamp;
    private String pushId;

    public WeatherSource(){
        this.timestamp = Calendar.getInstance().getTime() + "";
    }

    //Made as a builder to account for possibly differing response formats
    public static class Builder {
        private String sourceName;
        private Double currTemp;
        private Double currMax;
        private Double currMin;
        private Double currHumidity;
        private String summary;
        private String description;
        private String location;

        public Builder sourceName(String sourceName){this.sourceName = sourceName; return this;}
        public Builder summary(String summary){this.summary = summary; return this;}
        public Builder description(String description){this.description = description; return this;}
        public Builder location(String location){this.location = location; return this;}
        public Builder currTemp(Double currTemp){this.currTemp = currTemp; return this;}
        public Builder currMax(Double currMax){this.currMax = currMax; return this;}
        public Builder currMin(Double currMin){this.currMin = currMin; return this;}
        public Builder currHumidity(Double currHumidity){this.currHumidity = currHumidity; return this;}

        public WeatherSource build(){
            return new WeatherSource(this);
        }
    }

    private WeatherSource(Builder builder){
        this.sourceName = builder.sourceName;
        this.summary = builder.summary;
        this.description = builder.description;
        this.location = builder.location;
        this.currTemp = builder.currTemp;
        this.currMax = builder.currMax;
        this.currMin = builder.currMin;
        this.currHumidity = builder.currHumidity;
    }

    public String getSourceName(){return this.sourceName;}
    public String getSummary(){return this.summary;}
    public String getDescription(){return this.description;}
    public String getLocation(){return this.location;}
    public Double getCurrTemp(){return this.currTemp;}
    public Double getCurrMax(){return this.currMax;}
    public Double getCurrMin(){return this.currMin;}
    public Double getCurrHumidity(){return this.currHumidity;}
    public String getTimestamp(){return this.timestamp;}
    public String getPushId(){return this.pushId;}
    public void setPushId(String pushId){this.pushId = pushId;}

}
