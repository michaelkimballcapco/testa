package com.capco.weatherapp.weather;

import android.os.Handler;

import com.capco.weatherapp.ApplicationState;
import com.capco.weatherapp.GsonUtil;
import com.capco.weatherapp.R;
import com.capco.weatherapp.api.ApiListener;
import com.capco.weatherapp.api.weather.WeatherCallRunnable;
import com.capco.weatherapp.location.model.Location;
import com.capco.weatherapp.weather.model.CurrentWeather;

public class WeatherFragmentPresenter implements WeatherPresenter {

    private WeatherView weatherView;
    private WeatherIconMapper weatherIconMapper = new WeatherIconMapper();

    @Override
    public void registerWeatherView(WeatherView weatherView) {
        this.weatherView = weatherView;
        this.weatherView.initialize();
        callWeatherAPIAndUpdateView(weatherView.getLocation());
    }

    private void callWeatherAPIAndUpdateView(Location location){
        WeatherCallRunnable weatherCall = new WeatherCallRunnable(
                location, ApplicationState.getOpenWeatherAPIKey(),
                ApplicationState.getMeasurementSystem());
        weatherCall.registerListener(new ApiListener() {
            @Override
            public void update(Object data) {
                threadUpdateView((CurrentWeather) data);
            }
        });
        new Thread(weatherCall).start();
    }

    private void threadUpdateView(final CurrentWeather data){
        Handler mainHandler = new Handler(weatherView.getContext().getMainLooper());
        Runnable uiRunnable = new Runnable() {
            @Override
            public void run() {
                Integer id = weatherIconMapper.getIcon(data.getIconKey());
                if(id == null)
                    id = R.drawable.d01;
                weatherView.updateView(data, id);
            }
        };
        mainHandler.post(uiRunnable);

    }
}
