package com.capco.weatherapp.api.geocode.model;

import com.google.gson.annotations.SerializedName;

public class Geometry {
    public Area bounds;
    public Point location;
    @SerializedName("location_type")
    public String locationType;
    public Area viewport;
}
