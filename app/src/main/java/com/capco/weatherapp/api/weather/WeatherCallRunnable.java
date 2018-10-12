package com.capco.weatherapp.api.weather;

import android.util.Log;

import com.capco.weatherapp.GsonUtil;
import com.capco.weatherapp.api.ApiCallRunnable;
import com.capco.weatherapp.location.model.Location;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class WeatherCallRunnable extends ApiCallRunnable {
    private static final String TAG = "WeatherCall";
    private static final String API = "http://api.openweathermap.org/data/2.5/weather?lat=%1$f&lon=%2$f&appid=%3$s&units=%4$s";
    private URL url;
    public WeatherCallRunnable(Location location, String apiKey, String unit){
        String apiString = String.format(Locale.US, API, location.getLatitude(), location.getLongitude(), apiKey, unit);
        try {
            url = new URL(apiString);
        } catch(MalformedURLException e){
            Log.e(TAG, "URL is malformed: " + apiString, e);
        }
    }

    @Override
    public void run() {
        if(url == null)
            return;
        lastResult = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                lastResult = GsonUtil.getWeatherAPICurrentWeather(stringBuilder.toString());
                super.run();
            }
            finally{
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            Log.e(TAG, "Error connecting to Open Weather API", e);
        }
    }
}
