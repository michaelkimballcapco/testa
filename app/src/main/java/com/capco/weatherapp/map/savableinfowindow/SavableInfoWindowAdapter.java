package com.capco.weatherapp.map.savableinfowindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.capco.weatherapp.ApplicationState;
import com.capco.weatherapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class SavableInfoWindowAdapter implements GoogleMap.InfoWindowAdapter, SavableAdapter {

    private final View mWindow;

    public SavableInfoWindowAdapter(Context context) {
        mWindow = LayoutInflater.from(context).inflate(R.layout.view_savable_infowindow, null);
    }

    private void initialize(Marker marker){
        renderInfoWindowText(marker);
        ApplicationState.getMapPresenter()
                .getSavablePresenter(marker).registerAdapter(this);
    }

    private void renderInfoWindowText(Marker marker){
        String title = marker.getTitle();
        TextView city = mWindow.findViewById(R.id.point_title);
        city.setText(title);
    }

    @Override
    public void save(Marker marker) {
        ImageView saveImage = mWindow.findViewById(R.id.img_star);
        saveImage.setImageResource(android.R.drawable.star_on);
        marker.showInfoWindow();
    }

    @Override
    public void unsave(Marker marker) {
        ImageView saveImage = mWindow.findViewById(R.id.img_star);
        saveImage.setImageResource(android.R.drawable.star_off);
        marker.showInfoWindow();
    }

    @Override
    public View getInfoWindow(Marker marker) {
        initialize(marker);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        initialize(marker);
        return mWindow;
    }
}
