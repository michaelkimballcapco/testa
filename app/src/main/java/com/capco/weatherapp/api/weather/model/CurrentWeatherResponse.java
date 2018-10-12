package com.capco.weatherapp.api.weather.model;

import com.google.gson.annotations.SerializedName;

public class CurrentWeatherResponse {
    public Coord coord;
    public Weather[] weather;
    public String base;
    public Summary main;
    public Wind wind;
    public Cloud clouds;
    public Rain rain;
    public Snow snow;
    @SerializedName("dt")
    public long timeCalculated;
    public Sys sys;
    public long id;
    public String name;
    public int cod;
}
