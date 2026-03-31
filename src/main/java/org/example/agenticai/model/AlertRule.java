package org.example.agenticai.model;

public class AlertRule {

    private String city;
    private double temperatureThreshold;

    public AlertRule(String city, double temperatureThreshold) {
        this.city = city;
        this.temperatureThreshold = temperatureThreshold;
    }

    public String getCity() {
        return city;
    }

    public double getTemperatureThreshold() {
        return temperatureThreshold;
    }
}