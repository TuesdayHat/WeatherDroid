package com.tuesdayhat.weatherdroid;

import com.tuesdayhat.weatherdroid.models.WeatherSource;

public class Constants {
    public static final String OWM_KEY = BuildConfig.OWM_KEY;
    public static final String OWM_BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    public static final String OWM_KEY_QUERY_PARAMETER = "appid";
    public static final String OWM_LOCATION_QUERY_PARAMETER = "q";
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "locationHistory";
    public static final String FIREBASE_CHILD_SOURCE = "WeatherSource";
    public static final String PREFERENCES_LOCATION_KEY = "location";
    public static final String FIREBASE_CHILD_SAVED_REPORT = "source"; //TODO: come back to this
}
