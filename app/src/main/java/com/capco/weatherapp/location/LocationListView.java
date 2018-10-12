package com.capco.weatherapp.location;

import com.capco.weatherapp.location.model.Location;

import java.util.List;

public interface LocationListView {
    void loadLocations(List<Location> locations);
}
