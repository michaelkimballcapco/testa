package com.capco.weatherapp.api.geocode.model;

import com.google.gson.annotations.SerializedName;

public class PlusCode {
    @SerializedName("compound_code")
    public String compoundCode;
    @SerializedName("global_code")
    public String globalCode;
}
