package com.capco.weatherapp.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.capco.weatherapp.ApplicationState;
import com.capco.weatherapp.location.LocationListFragment;
import com.capco.weatherapp.map.MapFragment;
import com.capco.weatherapp.R;

public class MainActivity extends AppCompatActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApplicationState.getMainPresenter().registerActivity(this);
        ApplicationState.setGoogleAPIKey(getString(R.string.google_maps_key));
    }

    @Override
    public void switchToMapFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_frame, new MapFragment());
        ft.commit();
    }

    @Override
    public void switchToLocationFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_frame, new LocationListFragment());
        ft.commit();
    }

    @Override
    protected void onDestroy() {
        ApplicationState.getMainPresenter().unregisterActivity();
        super.onDestroy();
    }
}
