package com.capco.weatherapp.weather;

import com.capco.weatherapp.R;

import java.util.HashMap;
import java.util.Map;

public class WeatherIconMapper {
    private final Map<String, Integer> map;

    public WeatherIconMapper() {
        map = new HashMap<>();
        map.put("01d", R.drawable.d01);
        map.put("02d", R.drawable.d02);
        map.put("03d", R.drawable.d03);
        map.put("04d", R.drawable.d04);
        map.put("09d", R.drawable.d09);
        map.put("10d", R.drawable.d10);
        map.put("11d", R.drawable.d11);
        map.put("13d", R.drawable.d13);
        map.put("50d", R.drawable.dn50);

        map.put("01n", R.drawable.n01);
        map.put("02n", R.drawable.n02);
        map.put("03n", R.drawable.n03);
        map.put("04n", R.drawable.n04);
        map.put("09n", R.drawable.n09);
        map.put("10n", R.drawable.n10);
        map.put("11n", R.drawable.n11);
        map.put("13n", R.drawable.n13);
        map.put("50n", R.drawable.dn50);
    }

    public Integer getIcon(String key){
        return map.get(key);
    }
}
