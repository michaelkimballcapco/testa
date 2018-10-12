package com.capco.weatherapp.settings.service;

public interface SettingsService {
    void saveMeasurementSetting(boolean isImperial);
    String getMeasurementSetting();
    boolean isMetricMeasurement();
    String getDegreeMeasurement();
    String getDistanceMeasurement();
    String getSpeedMeasurement();
}
