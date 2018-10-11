package com.capco.weatherapp.map.savableinfowindow;

import com.google.android.gms.maps.model.Marker;

public interface SavablePresenter {
    void registerAdapter(SavableAdapter savableAdapter);
    void updateView(boolean isSaved, Marker marker);
}
