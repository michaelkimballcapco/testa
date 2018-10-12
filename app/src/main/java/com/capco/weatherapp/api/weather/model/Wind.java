package com.capco.weatherapp.api.weather.model;

import com.google.gson.annotations.SerializedName;

public class Wind {
    public double speed;
    @SerializedName("deg")
    public double direction;
}
