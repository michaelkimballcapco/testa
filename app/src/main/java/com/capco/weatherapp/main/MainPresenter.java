package com.capco.weatherapp.main;

import com.capco.weatherapp.location.LocationBookmarkService;

public interface MainPresenter {
    void registerActivity(MainView activity);
    void unregisterActivity();
    LocationBookmarkService getLocationBookmarkService();
}
