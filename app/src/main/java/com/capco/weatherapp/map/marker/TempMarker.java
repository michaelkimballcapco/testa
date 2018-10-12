package com.capco.weatherapp.map.marker;

import com.capco.weatherapp.ApplicationState;
import com.google.android.gms.maps.model.Marker;

public class TempMarker {
    private Marker marker;
    public TempMarker(){
        marker = null;
    }
    public void setActiveMarker(Marker marker){
        if((this.marker != null) && (!isSameMarker(marker)))
            removeOldUnsavedMarker();
        this.marker = marker;
    }
    private boolean isSameMarker(Marker marker){
        return this.marker.equals(marker);
    }
    private void removeOldUnsavedMarker(){
        if(!MarkerManager.getMarkerIsSaved(this.marker)) {
            this.marker.remove();
            ApplicationState.getMainPresenter().getLocationBookmarkService().removeLocation(this.marker.getTitle());
        }
    }
}
