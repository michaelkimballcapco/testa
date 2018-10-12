package com.capco.weatherapp.weather;

import android.content.Context;

import com.capco.weatherapp.location.model.Location;
import com.capco.weatherapp.weather.model.CurrentWeather;

public interface WeatherView {
    void initialize();
    void updateView(CurrentWeather currentWeather, int image);
    Context getContext();
    Location getLocation();
}
