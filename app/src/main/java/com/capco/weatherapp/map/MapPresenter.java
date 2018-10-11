package com.capco.weatherapp.map;

import com.capco.weatherapp.location.LocationBookmarkService;
import com.capco.weatherapp.map.savableinfowindow.SavablePresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public interface MapPresenter {
    void registerMapView(MapView mapView);
    void registerGoogleMap(GoogleMap googleMap);
    LocationBookmarkService getLocationBookmarkService();
    SavablePresenter getSavablePresenter(Marker marker);
    Marker getMarker(SavablePresenter savablePresenter);
}
