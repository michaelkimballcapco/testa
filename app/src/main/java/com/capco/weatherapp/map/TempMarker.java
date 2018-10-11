package com.capco.weatherapp.map;

import com.google.android.gms.maps.model.Marker;

public class TempMarker {
    private Marker marker;
    private boolean isSaved;
    public TempMarker(){
        marker = null;
        isSaved = false;
    }
    public void setMarker(Marker marker){
        if((this.marker != null) && (!isSaved))
            this.marker.remove();
        this.marker = marker;
        isSaved = false;
    }
    public void saveMarker(){
        isSaved = true;
    }
}
