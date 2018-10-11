package com.capco.weatherapp;

import com.capco.weatherapp.main.MainActivityPresenter;
import com.capco.weatherapp.main.MainPresenter;
import com.capco.weatherapp.map.MapFragmentPresenter;
import com.capco.weatherapp.map.MapPresenter;

public class ApplicationState {
    private static MainPresenter mainPresenter;
    private static MapPresenter mapPresenter;
    private static String googleAPIKey;
    private static String openWeatherAPIKey;
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
}
