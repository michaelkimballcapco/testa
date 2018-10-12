package com.capco.weatherapp.weather.model;

import com.capco.weatherapp.ApplicationState;

public class CurrentWeather {
    private double temperature;
    private double humidity;
    private double rainLevel;
    private double cloudiness;
    private double windSpeed;
    private double windDirection;
    private String iconKey;

    public double getTemperature() {
        return temperature;
    }

    public String getTemperatureStr(){
        return String.valueOf(temperature)
                + ApplicationState.getMainPresenter().getSettingsService().getDegreeMeasurement();
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getHumidityStr(){
        return String.valueOf(humidity) + "%";
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getRainLevel() {
        return rainLevel;
    }

    public String getRainLevelStr(){
        return String.valueOf(rainLevel)
                + ApplicationState.getMainPresenter().getSettingsService().getDistanceMeasurement();
    }

    public void setRainLevel(double rainLevel) {
        this.rainLevel = rainLevel;
    }

    public double getCloudiness() {
        return cloudiness;
    }

    public String getCloudinessStr(){
        return String.valueOf(cloudiness) + "%";
    }

    public void setCloudiness(double cloudiness) {
        this.cloudiness = cloudiness;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getWindSpeedStr(){
        return String.valueOf(windSpeed)
                + ApplicationState.getMainPresenter().getSettingsService().getSpeedMeasurement();
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public String getWindDirectionStr(){
        return String.valueOf(windDirection) + "°";
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public String getIconKey() {
        return iconKey;
    }

    public void setIconKey(String iconKey) {
        this.iconKey = iconKey;
    }
}
