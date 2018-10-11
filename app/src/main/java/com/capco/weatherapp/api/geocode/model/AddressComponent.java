package com.capco.weatherapp.api.geocode.model;

import com.google.gson.annotations.SerializedName;

public class AddressComponent {
    @SerializedName("long_name")
    private String longName;
    @SerializedName("short_name")
    private String shortName;
    private String[] types;
}
