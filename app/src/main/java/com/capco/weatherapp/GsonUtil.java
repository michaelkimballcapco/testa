package com.capco.weatherapp;

import com.capco.weatherapp.api.geocode.model.GeocodeResponse;
import com.capco.weatherapp.location.model.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

}
