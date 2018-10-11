package com.capco.weatherapp.map;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
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
        for(Marker marker: newMarkers){
            SavedMarker savedMarker = new SavedMarker(marker);
            savedMarker.toggleSave();
            markerIsSaved.put(savedMarker.toString(), savedMarker);
            Log.i(TAG, marker.getId() + ": " + marker.getTitle());
        }
        return newMarkers;
    }
}
