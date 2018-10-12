package com.capco.weatherapp.location.service;

import com.capco.weatherapp.location.model.Location;

import java.util.List;

public interface LocationBookmarkService {
    Location getLocation(String name);
    List<Location> getAllLocations();
    void saveLocation(Location location);
    void removeLocation(String name);
}
