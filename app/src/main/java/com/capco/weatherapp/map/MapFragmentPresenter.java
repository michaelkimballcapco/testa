package com.capco.weatherapp.map;

import android.content.Context;
import android.os.Handler;

import com.capco.weatherapp.ApplicationState;
import com.capco.weatherapp.api.ApiListener;
import com.capco.weatherapp.api.geocode.GeocodeCallRunnable;
import com.capco.weatherapp.location.Location;
import com.capco.weatherapp.location.LocationBookmarkService;
import com.capco.weatherapp.location.LocationBookmarkSharedPreferencesRepository;
import com.capco.weatherapp.map.savableinfowindow.SavableInfoWindowAdapter;
import com.capco.weatherapp.map.savableinfowindow.SavableInfoWindowPresenter;
import com.capco.weatherapp.map.savableinfowindow.SavablePresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapFragmentPresenter implements MapPresenter{
    private static final String TAG = "MapFragmentPresenter";
    private MapView mapView;
    private GoogleMap googleMap;
    private LocationBookmarkService locationBookmarkService;
    private TempMarker tempMarker = new TempMarker();
    private Map<Marker, SavablePresenter> savablePresenters = new HashMap<>();
    @Override
    public void registerMapView(MapView mapView) {
        this.mapView = mapView;
        mapView.initialize();
        resetLocationBookmarkService();
    }

    @Override
    public void registerGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
        List<Marker> markers;
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                addPoint(latLng);
            }
        });
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(MarkerManager.getMarkerIsSaved(marker)) {
                    tempMarker.setActiveMarker(marker);
                }
                SavablePresenter savablePresenter = getSavablePresenter(marker);
                savablePresenter.updateView(MarkerManager.getMarkerIsSaved(marker), marker);
                return false;
            }
        });
        googleMap.setInfoWindowAdapter(new SavableInfoWindowAdapter(mapView.getContext()));
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                SavablePresenter savablePresenter = getSavablePresenter(marker);
                savablePresenter.updateView(MarkerManager.toggleMarker(marker), marker);
                if(MarkerManager.getMarkerIsSaved(marker)) {
                    Location location = MarkerManager.transformToLocation(marker);
                    getLocationBookmarkService().saveLocation(location);
                }
            }
        });

        if(MarkerManager.isEmpty())
            markers = MarkerManager.loadMarkers(getLocationBookmarkService().getAllLocations(), googleMap);
        else
            markers = MarkerManager.recreateMarkers(googleMap);
        recreatePresenters(markers);
    }

    @Override
    public SavablePresenter getSavablePresenter(Marker marker){
        SavablePresenter presenter = savablePresenters.get(marker);
        if(presenter == null) {
            presenter = new SavableInfoWindowPresenter();
            savablePresenters.put(marker, presenter);
        }
        return presenter;
    }

    @Override
    public Marker getMarker(SavablePresenter presenter){
        for(Map.Entry<Marker, SavablePresenter> entry: savablePresenters.entrySet()) {
            if (presenter.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public LocationBookmarkService getLocationBookmarkService() {
        return locationBookmarkService;
    }

    private void resetLocationBookmarkService(){
        locationBookmarkService = new LocationBookmarkSharedPreferencesRepository(
                mapView.getContext().getSharedPreferences(
                        LocationBookmarkSharedPreferencesRepository.PREFERENCE_PACKAGE,
                        Context.MODE_PRIVATE));
    }

    private void recreatePresenters(List<Marker> newMarkers){
        savablePresenters.clear();
        for(Marker marker: newMarkers){
            savablePresenters.put(marker, new SavableInfoWindowPresenter());
        }
    }

    private void addPoint(LatLng point){
        if(googleMap == null)
            return;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        Marker marker = googleMap.addMarker(markerOptions);
        MarkerManager.addMarker(marker);
        tempMarker.setActiveMarker(marker);
        callGeocodeForCityAndUpdateInfoWindow(marker);
    }

    private void callGeocodeForCityAndUpdateInfoWindow(final Marker marker){
        GeocodeCallRunnable geocodeCall = new GeocodeCallRunnable(marker.getPosition(), ApplicationState.getGoogleAPIKey());
        geocodeCall.registerListener(new ApiListener() {
            @Override
            public void update(Object data) {
                updateSavableInfoWindow((String) data, marker);
            }
        });
        new Thread(geocodeCall).start();
    }

    private void updateSavableInfoWindow(final String city, final Marker marker){
        Handler mainHandler = new Handler(mapView.getContext().getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                marker.setTitle(city);
                SavablePresenter savablePresenter = getSavablePresenter(marker);
                savablePresenter.updateView(MarkerManager.getMarkerIsSaved(marker), marker);
                marker.showInfoWindow();
            }
        };
        mainHandler.post(myRunnable);
    }
}
