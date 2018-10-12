package com.capco.weatherapp.location.service;

import android.content.SharedPreferences;

import com.capco.weatherapp.GsonUtil;
import com.capco.weatherapp.location.model.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocationBookmarkSharedPreferencesRepository implements LocationBookmarkService {

    public static final String PREFERENCE_PACKAGE = "com.capco.weatherapp.location";

    private SharedPreferences prefs;

    public LocationBookmarkSharedPreferencesRepository(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    @Override
    public Location getLocation(String name) {
        String location = prefs.getString(name, null);
        if(location == null)
            return null;
        return GsonUtil.getLocation(location);
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        Map<String, ?> entries = prefs.getAll();
        for (Map.Entry<String, ?> entry : entries.entrySet()) {
            locations.add(GsonUtil.getLocation((String) entry.getValue()));
        }
        return locations;
    }

    @Override
    public void saveLocation(Location location) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(location.getName(), GsonUtil.getLocationJSON(location));
        editor.apply();
    }

    @Override
    public void removeLocation(String name) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(name);
        editor.apply();
    }
}
