package com.capco.weatherapp.main;

import android.content.Context;

import com.capco.weatherapp.location.model.Location;
import com.capco.weatherapp.location.service.LocationBookmarkService;
import com.capco.weatherapp.location.service.LocationBookmarkSharedPreferencesRepository;
import com.capco.weatherapp.settings.service.SettingsService;
import com.capco.weatherapp.settings.service.SettingsSharedPreferencesRepository;

public class MainActivityPresenter implements MainPresenter{

    private MainView mainView;
    private LocationBookmarkService locationBookmarkService;
    private SettingsService settingsService;

    @Override
    public void registerActivity(MainView mainView) {
        this.mainView = mainView;
        this.mainView.switchToLocationFragment();
        resetLocationBookmarkService();
        resetSettingsService();
    }

    @Override
    public void unregisterActivity() {
        this.mainView = null;
    }

    @Override
    public LocationBookmarkService getLocationBookmarkService() {
        return locationBookmarkService;
    }

    @Override
    public SettingsService getSettingsService() {
        return settingsService;
    }

    private void resetLocationBookmarkService(){
        locationBookmarkService = new LocationBookmarkSharedPreferencesRepository(
                mainView.getApplicationContext().getSharedPreferences(
                        LocationBookmarkSharedPreferencesRepository.PREFERENCE_PACKAGE,
                        Context.MODE_PRIVATE));
    }

    private void resetSettingsService(){
        settingsService = new SettingsSharedPreferencesRepository(
                mainView.getApplicationContext().getSharedPreferences(
                        SettingsSharedPreferencesRepository.PREFERENCE_PACKAGE,
                        Context.MODE_PRIVATE));
    }

    @Override
    public void switchToMapFragment() {
        mainView.switchToMapFragment();
    }

    @Override
    public void switchToLocationFragment() {
        mainView.switchToLocationFragment();
    }

    @Override
    public void switchToWeatherFragment(Location location) {
        mainView.switchToWeatherFragment(location);
    }

    @Override
    public void switchToHelpFragment() {
        mainView.switchToHelpFragment();
    }
}
