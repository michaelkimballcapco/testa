package com.capco.weatherapp;

import com.capco.weatherapp.api.geocode.model.GeocodeResponse;
import com.capco.weatherapp.api.weather.model.CurrentWeatherResponse;
import com.capco.weatherapp.location.model.Location;
import com.capco.weatherapp.weather.model.CurrentWeather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public class GsonUtil {
    private GsonUtil(){}
    private static Gson getGson(){
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }
    public static String getGeocodeAPICity(String json){
        Gson gson = getGson();
        GeocodeResponse geocodeResponse = gson.fromJson(json, GeocodeResponse.class);
        if(geocodeResponse.results.length > 0)
            return geocodeResponse.results[0].formattedAddress;
        else
            return "Unknown Location";
    }
    public static Location getLocation(String json){
        Gson gson = getGson();
        return gson.fromJson(json, Location.class);
    }
    public static String getLocationJSON(Location location){
        Gson gson = getGson();
        return gson.toJson(location);
    }
    public static CurrentWeather getWeatherAPICurrentWeather(String json){
        Gson gson = getGson();
        CurrentWeatherResponse currentWeatherResponse = gson.fromJson(json, CurrentWeatherResponse.class);
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setTemperature(currentWeatherResponse.main.temp);
        currentWeather.setHumidity(currentWeatherResponse.main.humidity);
        double rainVol = currentWeatherResponse.rain != null ? currentWeatherResponse.rain.threeHourVolume : 0;
        currentWeather.setRainLevel(rainVol);
        currentWeather.setWindSpeed(currentWeatherResponse.wind.speed);
        currentWeather.setWindDirection(currentWeatherResponse.wind.direction);
        if((currentWeatherResponse.weather != null) && (currentWeatherResponse.weather.length > 0))
            currentWeather.setIconKey(currentWeatherResponse.weather[0].icon);
        return currentWeather;
    }

}
