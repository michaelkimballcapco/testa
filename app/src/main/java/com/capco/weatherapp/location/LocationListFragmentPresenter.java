package com.capco.weatherapp.location;

import com.capco.weatherapp.ApplicationState;

public class LocationListFragmentPresenter implements LocationListPresenter {

    private LocationListView locationListView;

    @Override
    public void registerLocationListView(LocationListView locationListView) {
        this.locationListView = locationListView;
        this.locationListView.loadLocations(ApplicationState.getMainPresenter().getLocationBookmarkService().getAllLocations());
    }
}
