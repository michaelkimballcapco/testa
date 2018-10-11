package com.capco.weatherapp.location;

import java.util.List;

public interface LocationBookmarkService {
    Location getLocation(String name);
    List<Location> getAllLocations();
    void saveLocation(Location location);
    void removeLocation(String name);
}
