package com.capco.weatherapp.map.marker;

import com.capco.weatherapp.location.model.Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MarkerManager {
    private static final String TAG = "MarkerManager";
    private static Map<String, SavedMarker> markerIsSaved = new HashMap<>();

    public static boolean isEmpty(){
        return markerIsSaved.isEmpty();
    }

    public static void addMarker(Marker marker) {
        SavedMarker savedMarker = new SavedMarker(marker);
        markerIsSaved.put(savedMarker.toString(), savedMarker);
    }

    public static boolean toggleMarker(Marker marker){
        SavedMarker savedMarker = markerIsSaved.get(marker.getId());
        savedMarker = addIfNull(savedMarker, marker);
        savedMarker.toggleSave();
        return savedMarker.isSaved();
    }
    public static boolean getMarkerIsSaved(Marker marker){
        SavedMarker savedMarker = markerIsSaved.get(marker.getId());
        savedMarker = addIfNull(savedMarker, marker);
        return savedMarker.isSaved();
    }
    public static void removeMarker(Marker marker){
        markerIsSaved.remove(marker.getId());
    }

    private static SavedMarker addIfNull(SavedMarker savedMarker, Marker marker){
        if(savedMarker == null) {
            addMarker(marker);
            savedMarker = markerIsSaved.get(marker.getId());
        }
        return savedMarker;
    }

    public static List<Marker> getSavedMarkers(){
        Iterator<Map.Entry<String, SavedMarker>> iterator = markerIsSaved.entrySet().iterator();
        List<Marker> savedMarkers = new ArrayList<>();
        while(iterator.hasNext())
        {
            Map.Entry<String, SavedMarker> entry = iterator.next();
            if(entry.getValue().isSaved())
                savedMarkers.add(entry.getValue().getMarker());
            else
                markerIsSaved.remove(entry.getKey());
        }
        return savedMarkers;
    }

    public static List<Marker> recreateMarkers(GoogleMap map){
        List<Marker> newMarkers = getNewMarkersAndRemoveOld(map);
        addListOfMarkers(newMarkers);
        return newMarkers;
    }

    private static List<Marker> getNewMarkersAndRemoveOld(GoogleMap map){
        Iterator<Map.Entry<String, SavedMarker>> iterator = markerIsSaved.entrySet().iterator();
        List<Marker> newMarkers = new ArrayList<>();
        while(iterator.hasNext())
        {
            Map.Entry<String, SavedMarker> entry = iterator.next();
            iterator.remove();
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(entry.getValue().getMarker().getTitle());
            markerOptions.position(entry.getValue().getMarker().getPosition());
            Marker marker = map.addMarker(markerOptions);
            newMarkers.add(marker);
        }
        return newMarkers;
    }

    public static List<Marker> loadMarkers(List<Location> locations, GoogleMap map){
        markerIsSaved.clear();
        List<Marker> markers = getMarkersFromLocations(locations, map);
        addListOfMarkers(markers);
        return markers;
    }

    private static List<Marker> getMarkersFromLocations(List<Location> locations, GoogleMap map){
        List<Marker> markers = new ArrayList<>();
        for(Location location: locations){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(location.getName())
                    .position(new LatLng(location.getLatitude(), location.getLongitude()));
            Marker marker = map.addMarker(markerOptions);
            markers.add(marker);
        }
        return markers;
    }

    private static void addListOfMarkers(List<Marker> markers){
        for(Marker marker: markers){
            SavedMarker savedMarker = new SavedMarker(marker);
            savedMarker.toggleSave();
            markerIsSaved.put(savedMarker.toString(), savedMarker);
        }
    }

    public static Location transformToLocation(Marker marker){
        Location location = new Location();
        location.setName(marker.getTitle());
        location.setLongitude(marker.getPosition().longitude);
        location.setLatitude(marker.getPosition().latitude);
        return location;
    }
}
