package com.capco.weatherapp.api.weather.model;

import com.google.gson.annotations.SerializedName;

public class Sys {
    public int type;
    public long id;
    public double message;
    @SerializedName("country")
    public String countryCode;
    public long sunrise;
    public long sunset;
}
