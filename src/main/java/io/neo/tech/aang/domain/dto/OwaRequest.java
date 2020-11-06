package io.neo.tech.aang.domain.dto;

import java.util.Optional;

public class OwaRequest {
    private Optional<String> city;
    private Optional<String> language;
    private Optional<String> unit;
    private Optional<Double> longitude;
    private Optional<Double> latitude;

    public OwaRequest() {
    }

    public Optional<String> getCity() {
        return city;
    }

    public void setCity(Optional<String> city) {
        this.city = city;
    }

    public Optional<String> getLanguage() {
        return language;
    }

    public void setLanguage(Optional<String> language) {
        this.language = language;
    }

    public Optional<String> getUnit() {
        return unit;
    }

    public void setUnit(Optional<String> unit) {
        this.unit = unit;
    }

    public Optional<Double> getLongitude() {
        return longitude;
    }

    public void setLongitude(Optional<Double> longitude) {
        this.longitude = longitude;
    }

    public Optional<Double> getLatitude() {
        return latitude;
    }

    public void setLatitude(Optional<Double> latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "OwaRequest {" + "\n" +
                "city=" + city + "\n" +
                "language=" + language + "\n" +
                "unit=" + unit + "\n" +
                "longitude=" + longitude + "\n" +
                "latitude=" + latitude + "\n" +
                '}';
    }
}
