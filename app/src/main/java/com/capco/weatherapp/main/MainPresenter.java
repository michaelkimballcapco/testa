package com.capco.weatherapp.main;

import com.capco.weatherapp.location.model.Location;
import com.capco.weatherapp.location.service.LocationBookmarkService;
import com.capco.weatherapp.settings.service.SettingsService;

public interface MainPresenter {
    void registerActivity(MainView activity);
    void unregisterActivity();
    void switchToMapFragment();
    void switchToLocationFragment();
    void switchToWeatherFragment(Location location);
    void switchToHelpFragment();
    LocationBookmarkService getLocationBookmarkService();
    SettingsService getSettingsService();
}
