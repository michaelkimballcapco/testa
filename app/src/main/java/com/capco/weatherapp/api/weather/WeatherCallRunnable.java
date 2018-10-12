package com.capco.weatherapp.api.weather;

import android.util.Log;

import com.capco.weatherapp.GsonUtil;
import com.capco.weatherapp.api.ApiCallRunnable;
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
    private static final String API = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%1$f,%2$f&key=%3$s";
    private URL url;
    public WeatherCallRunnable(LatLng point, String apiKey){
        String apiString = String.format(Locale.US, API, point.latitude, point.longitude, apiKey);
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
                lastResult = GsonUtil.getGeocodeAPICity(stringBuilder.toString());
                super.run();
            }
            finally{
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            Log.e(TAG, "Error connecting to Google Maps API", e);
        }
    }
}
