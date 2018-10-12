package com.capco.weatherapp;

import com.capco.weatherapp.location.LocationListFragmentPresenter;
import com.capco.weatherapp.location.LocationListPresenter;
import com.capco.weatherapp.main.MainActivityPresenter;
import com.capco.weatherapp.main.MainPresenter;
import com.capco.weatherapp.map.MapFragmentPresenter;
import com.capco.weatherapp.map.MapPresenter;
import com.capco.weatherapp.weather.WeatherFragmentPresenter;
import com.capco.weatherapp.weather.WeatherPresenter;

public class ApplicationState {

    private static MainPresenter mainPresenter;
    private static MapPresenter mapPresenter;
    private static WeatherPresenter weatherPresenter;
    private static LocationListPresenter locationListPresenter;
    private static String googleAPIKey;
    private static String openWeatherAPIKey;
    private static String measurementSystem;

    public static MainPresenter getMainPresenter(){
        if(mainPresenter == null)
            mainPresenter = new MainActivityPresenter();
        return mainPresenter;
    }
    public static MapPresenter getMapPresenter(){
        if(mapPresenter == null)
            mapPresenter = new MapFragmentPresenter();
        return mapPresenter;
    }

    public static WeatherPresenter getWeatherPresenter(){
        if(weatherPresenter == null)
            weatherPresenter = new WeatherFragmentPresenter();
        return weatherPresenter;
    }

    public static LocationListPresenter getLocationListPresenter(){
        if(locationListPresenter == null)
            locationListPresenter = new LocationListFragmentPresenter();
        return locationListPresenter;
    }

    public static String getGoogleAPIKey() {
        return googleAPIKey;
    }

    public static void setGoogleAPIKey(String googleAPIKey) {
        ApplicationState.googleAPIKey = googleAPIKey;
    }

    public static String getOpenWeatherAPIKey() {
        return openWeatherAPIKey;
    }

    public static void setOpenWeatherAPIKey(String openWeatherAPIKey) {
        ApplicationState.openWeatherAPIKey = openWeatherAPIKey;
    }

    public static String getMeasurementSystem() {
        return "imperial";
    }

    public static void setMeasurementSystem(String measurementSystem) {
        ApplicationState.measurementSystem = measurementSystem;
    }
}
