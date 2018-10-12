package com.capco.weatherapp.map.marker;

import com.google.android.gms.maps.model.Marker;

public class SavedMarker {
    private boolean saved;
    private Marker marker;
    public SavedMarker(Marker marker){
        this.saved = false;
        this.marker = marker;
    }
    public boolean toggleSave(){
        this.saved = !this.saved;
        return this.saved;
    }

    public boolean isSaved() {
        return saved;
    }

    public Marker getMarker(){
        return marker;
    }

    @Override
    public String toString() {
        return marker.getId();
    }
}
