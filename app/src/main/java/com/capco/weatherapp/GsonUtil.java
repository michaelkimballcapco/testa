package com.capco.weatherapp;

import com.capco.weatherapp.api.geocode.model.GeocodeResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    private GsonUtil(){}
    public static String getGeocodeAPICity(String json){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        GeocodeResponse geocodeResponse = gson.fromJson(json, GeocodeResponse.class);
        if(geocodeResponse.results.length > 0)
            return geocodeResponse.results[0].formattedAddress;
        else
            return "Unknown Location";
    }
}
