package com.capco.weatherapp.map;

import android.util.Log;

import com.capco.weatherapp.ApplicationState;
import com.capco.weatherapp.api.ApiListener;
import com.capco.weatherapp.api.geocode.GeocodeCallRunnable;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragmentPresenter implements MapPresenter{
    private static final String TAG = "MapFragmentPresenter";
    private MapView mapView;
    private GoogleMap googleMap;
    private TempMarker tempMarker = new TempMarker();
    @Override
    public void registerMapView(MapView mapView) {
        this.mapView = mapView;
        mapView.initialize();
    }

    @Override
    public void registerGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                addPoint(latLng);
            }
        });
    }

    private void addPoint(LatLng point){
        if(googleMap == null)
            return;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        markerOptions.title("My new point");
        Marker marker = googleMap.addMarker(markerOptions);
        tempMarker.setMarker(marker);
        updateInfoWindow(point);
    }

    private void updateInfoWindow(LatLng point){
        GeocodeCallRunnable geocodeCall = new GeocodeCallRunnable(point, ApplicationState.getGoogleAPIKey());
        geocodeCall.registerListener(new ApiListener() {
            @Override
            public void update(Object data) {
                Log.i(TAG, (String) data);
            }
        });
        new Thread(geocodeCall).start();
    }
}
