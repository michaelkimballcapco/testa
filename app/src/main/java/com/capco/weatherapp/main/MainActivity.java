package com.capco.weatherapp.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.capco.weatherapp.ApplicationState;
import com.capco.weatherapp.help.HelpFragment;
import com.capco.weatherapp.location.LocationListFragment;
import com.capco.weatherapp.location.model.Location;
import com.capco.weatherapp.map.MapFragment;
import com.capco.weatherapp.R;
import com.capco.weatherapp.weather.WeatherFragment;

public class MainActivity extends AppCompatActivity implements MainView {

    private boolean initialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApplicationState.getMainPresenter().registerActivity(this);
        ApplicationState.setGoogleAPIKey(getString(R.string.google_maps_key));
        ApplicationState.setOpenWeatherAPIKey(getString(R.string.open_weather_key));
    }

    @Override
    public void switchToMapFragment() {
        switchFragment(new MapFragment());
    }

    @Override
    public void switchToLocationFragment() {
        switchFragment(new LocationListFragment());
    }

    @Override
    public void switchToWeatherFragment(Location location) {
        WeatherFragment weatherFragment = new WeatherFragment();
        weatherFragment.setLocation(location);
        switchFragment(weatherFragment);
    }

    @Override
    public void switchToHelpFragment() {
        switchFragment(new HelpFragment());
    }

    private void switchFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_frame, fragment);
        if(initialized)
            ft.addToBackStack(null);
        ft.commit();
        initialized = true;
    }

    @Override
    protected void onDestroy() {
        ApplicationState.getMainPresenter().unregisterActivity();
        super.onDestroy();
    }
}
