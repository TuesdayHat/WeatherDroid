package com.tuesdayhat.weatherdroid.services;

import android.util.Log;

import com.tuesdayhat.weatherdroid.Constants;
import com.tuesdayhat.weatherdroid.models.WeatherSource;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OpenWeatherMapService {
    public static void currentWeather(String location, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.OWM_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.OWM_LOCATION_QUERY_PARAMETER, location + ",us");
        urlBuilder.addQueryParameter(Constants.OWM_KEY_QUERY_PARAMETER, Constants.OWM_KEY);
        String url = urlBuilder.build().toString();;

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.OWM_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<WeatherSource> processResults(Response response) {
        ArrayList<WeatherSource> weatherSources = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject openWeatherMapJSON = new JSONObject(jsonData);

            String summary = openWeatherMapJSON.getJSONArray("weather").getJSONObject(0).getString("main");
            String description = openWeatherMapJSON.getJSONArray("weather").getJSONObject(0).getString("description");
            String location = openWeatherMapJSON.getString("name") + ", " + openWeatherMapJSON.getJSONObject("sys").getString("country");
            Double temp = openWeatherMapJSON.getJSONObject("main").getDouble("temp");
            Double max = openWeatherMapJSON.getJSONObject("main").getDouble("temp_max");
            Double min = openWeatherMapJSON.getJSONObject("main").getDouble("temp_min");
            Double humidity = openWeatherMapJSON.getJSONObject("main").getDouble("humidity");

            WeatherSource weatherSource = new WeatherSource.Builder()
                    .sourceName("Open Weather Map")
                    .summary(summary)
                    .description(description)
                    .location(location)
                    .currTemp(temp)
                    .currMax(max)
                    .currMin(min)
                    .currHumidity(humidity)
                    .timestamp(Calendar.getInstance().getTime()+"")
                    .build();

            weatherSources.add(weatherSource);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return weatherSources;
    }
}
