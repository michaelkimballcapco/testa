package com.capco.weatherapp.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.capco.weatherapp.ApplicationState;
import com.capco.weatherapp.R;
import com.capco.weatherapp.location.model.Location;
import com.capco.weatherapp.weather.model.CurrentWeather;

public class WeatherFragment extends Fragment implements WeatherView {

    private Location location;
    private View view;

    public void setLocation(Location location){
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_city,
                container, false);
        ApplicationState.getWeatherPresenter().registerWeatherView(this);
        return view;
    }

    @Override
    public void initialize() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(location.getName());
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void updateView(CurrentWeather currentWeather, int image) {
        ImageView backdrop = view.findViewById(R.id.backdrop);
        backdrop.setImageResource(image);
        TextView temperature = view.findViewById(R.id.tv_temperature);
        temperature.setText(currentWeather.getTemperatureStr());
        TextView humidity = view.findViewById(R.id.tv_humidity);
        humidity.setText(currentWeather.getHumidityStr());
        TextView rainLevel = view.findViewById(R.id.tv_rain_level);
        rainLevel.setText(currentWeather.getRainLevelStr());
        TextView cloudiness = view.findViewById(R.id.tv_cloudiness);
        cloudiness.setText(currentWeather.getCloudinessStr());
        TextView windSpeed = view.findViewById(R.id.tv_wind_speed);
        windSpeed.setText(currentWeather.getWindSpeedStr());
        TextView windDirection = view.findViewById(R.id.tv_wind_direction);
        windDirection.setText(currentWeather.getWindDirectionStr());
    }
}
