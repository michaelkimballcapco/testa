package com.capco.weatherapp.main;

import android.content.Context;

import com.capco.weatherapp.location.model.Location;

public interface MainView {
    void switchToMapFragment();
    void switchToLocationFragment();
    void switchToWeatherFragment(Location location);
    void switchToHelpFragment();
    Context getApplicationContext();
}
