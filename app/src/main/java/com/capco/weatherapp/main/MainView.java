package com.capco.weatherapp.main;

import android.content.Context;

public interface MainView {
    void switchToMapFragment();
    void switchToLocationFragment();
    Context getApplicationContext();
}
