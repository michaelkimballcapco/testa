package com.capco.weatherapp.api.geocode.model;

import com.google.gson.annotations.SerializedName;

public class GeocodeResponse {
    @SerializedName("plus_code")
    public PlusCode plusCode;
    public GeocodeResult[] results;
    public String status;
}
