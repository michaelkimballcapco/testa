package com.capco.weatherapp.api.weather.model;

import com.google.gson.annotations.SerializedName;

public class Summary {
    public double temp;
    public double pressure;
    public double humidity;
    @SerializedName("temp_min")
    public double tempMin;
    @SerializedName("temp_max")
    public double tempMax;
    @SerializedName("sea_level")
    public double pressureSeaLevel;
    @SerializedName("grnd_level")
    public double pressureGroundLevel;
}
