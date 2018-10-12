package com.capco.weatherapp.main;

import android.content.Context;

import com.capco.weatherapp.location.service.LocationBookmarkService;
import com.capco.weatherapp.location.service.LocationBookmarkSharedPreferencesRepository;

public class MainActivityPresenter implements MainPresenter{

    private MainView mainView;
    private LocationBookmarkSharedPreferencesRepository locationBookmarkService;

    @Override
    public void registerActivity(MainView mainView) {
        this.mainView = mainView;
        this.mainView.switchToLocationFragment();
        resetLocationBookmarkService();
    }

    @Override
    public void unregisterActivity() {
        this.mainView = null;
    }

    @Override
    public LocationBookmarkService getLocationBookmarkService() {
        return locationBookmarkService;
    }

    private void resetLocationBookmarkService(){
        locationBookmarkService = new LocationBookmarkSharedPreferencesRepository(
                mainView.getApplicationContext().getSharedPreferences(
                        LocationBookmarkSharedPreferencesRepository.PREFERENCE_PACKAGE,
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
}
