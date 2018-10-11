package com.capco.weatherapp.map;

import com.google.android.gms.maps.GoogleMap;

public interface MapPresenter {
    void registerMapView(MapView mapView);
    void registerGoogleMap(GoogleMap googleMap);
}
