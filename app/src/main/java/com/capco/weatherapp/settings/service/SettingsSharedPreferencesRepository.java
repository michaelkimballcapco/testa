package com.capco.weatherapp.settings.service;

import android.content.SharedPreferences;

import com.capco.weatherapp.GsonUtil;

public class SettingsSharedPreferencesRepository implements SettingsService {
    public static final String PREFERENCE_PACKAGE = "com.capco.weatherapp.settings";

    private static final String MEASUREMENT_KEY = "measurement.system";
    private static final String MEASUREMENT_IMPERIAL = "imperial";
    private static final String MEASUREMENT_METRIC = "metric";

    private SharedPreferences prefs;

    public SettingsSharedPreferencesRepository(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    @Override
    public void saveMeasurementSetting(boolean isMetric) {
        SharedPreferences.Editor editor = prefs.edit();
        String measurement = isMetric ? MEASUREMENT_METRIC : MEASUREMENT_IMPERIAL;
        editor.putString(MEASUREMENT_KEY, measurement);
        editor.apply();
    }

    @Override
    public String getMeasurementSetting() {
        return prefs.getString(MEASUREMENT_KEY, MEASUREMENT_IMPERIAL);
    }

    @Override
    public boolean isMetricMeasurement() {
        return prefs.getString(MEASUREMENT_KEY, MEASUREMENT_IMPERIAL).equals(MEASUREMENT_METRIC);
    }

    @Override
    public String getDegreeMeasurement() {
        return prefs.getString(MEASUREMENT_KEY, MEASUREMENT_IMPERIAL).equals(MEASUREMENT_IMPERIAL) ? "F" : "C";
    }

    @Override
    public String getDistanceMeasurement() {
        return prefs.getString(MEASUREMENT_KEY, MEASUREMENT_IMPERIAL).equals(MEASUREMENT_IMPERIAL) ? "in" : "mm";
    }

    @Override
    public String getSpeedMeasurement() {
        return prefs.getString(MEASUREMENT_KEY, MEASUREMENT_IMPERIAL).equals(MEASUREMENT_IMPERIAL) ? "mph" : "kph";
    }
}
