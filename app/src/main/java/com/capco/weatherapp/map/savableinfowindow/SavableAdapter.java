package com.capco.weatherapp.map.savableinfowindow;

import com.google.android.gms.maps.model.Marker;

public interface SavableAdapter {
    void save(Marker marker);
    void unsave(Marker marker);
}
