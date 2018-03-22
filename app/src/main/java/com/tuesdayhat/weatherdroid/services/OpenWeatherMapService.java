package com.tuesdayhat.weatherdroid.services;

import com.tuesdayhat.weatherdroid.Constants;
import com.tuesdayhat.weatherdroid.models.WeatherSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.OPENWEATHERMAP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.LOCATION_QUERY_PARAMETER, location);
        urlBuilder.addQueryParameter(Constants.OPENWEATHERMAP_KEY_QUERY_PARAMETER, Constants.OPENWEATHERMAP_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.OPENWEATHERMAP_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<WeatherSource> processResults(Response response) {
        ArrayList<WeatherSource> weatherSources = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject openWatherMapJSON = new JSONObject(jsonData);

            String summary = openWatherMapJSON.getJSONArray("weather").getJSONObject(0).getString("main");
            String description = openWatherMapJSON.getJSONArray("weather").getJSONObject(0).getString("description");
            String location = openWatherMapJSON.getString("name") + ", " + openWatherMapJSON.getJSONObject("sys").getString("country");
            Double temp = openWatherMapJSON.getJSONObject("main").getDouble("temp");
            Double max = openWatherMapJSON.getJSONObject("main").getDouble("temp_max");
            Double min = openWatherMapJSON.getJSONObject("main").getDouble("temp_min");
            Double humidity = openWatherMapJSON.getJSONObject("main").getDouble("humidity");

            WeatherSource weatherSource = new WeatherSource.Builder()
                    .sourceName("Open Weather Map")
                    .summary(summary)
                    .description(description)
                    .location(location)
                    .currTemp(temp)
                    .currMax(max)
                    .currMin(min)
                    .currHumidity(humidity)
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
