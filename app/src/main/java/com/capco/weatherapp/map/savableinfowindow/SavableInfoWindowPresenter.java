package com.capco.weatherapp.map.savableinfowindow;

import com.capco.weatherapp.ApplicationState;
import com.capco.weatherapp.map.MarkerManager;
import com.google.android.gms.maps.model.Marker;

public class SavableInfoWindowPresenter implements SavablePresenter {

    private SavableAdapter savableAdapter;

    @Override
    public void registerAdapter(SavableAdapter savableAdapter){
        if((this.savableAdapter != null) && (this.savableAdapter.equals(savableAdapter)))
            return;
        this.savableAdapter = savableAdapter;
        Marker marker = ApplicationState.getMapPresenter().getMarker(this);
        if(marker != null)
            updateView(MarkerManager.getMarkerIsSaved(marker), marker);
    }

    @Override
    public void updateView(boolean isSaved, Marker marker) {
        if(savableAdapter == null)
            return;
        if(isSaved)
            savableAdapter.save(marker);
        else {
            MarkerManager.removeMarker(marker);
            savableAdapter.unsave(marker);
        }
    }
}
