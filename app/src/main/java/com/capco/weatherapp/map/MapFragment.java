package com.capco.weatherapp.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capco.weatherapp.ApplicationState;
import com.capco.weatherapp.R;
import com.capco.weatherapp.map.MapView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends Fragment implements OnMapReadyCallback, MapView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,
                container, false);
        ApplicationState.getMapPresenter().registerMapView(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ApplicationState.getMapPresenter().registerGoogleMap(googleMap);
    }

    @Override
    public void initialize() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);
    }


}
