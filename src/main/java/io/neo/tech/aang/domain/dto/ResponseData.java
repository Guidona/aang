package io.neo.tech.aang.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponseData {

    private Double temperature;
    private Double feels_like;
    private Double min_temperature;
    private Double max_temperature;
    private String icon_url;
    private Details details;
    private WindResponse wind;
    private SunSetAndSunRise sunSetAndSunRise;
    private List<WeatherData> weatherData;

    public ResponseData() {
    }

    public List<WeatherData> getWeatherData() {
        return weatherData;
    }

    /*public WeatherData getWD(){
        return new WeatherData();
    }

    public List<WeatherData> getWDL(){
        List<WeatherData> weatherDataList = new ArrayList<>();
        return weatherDataList;
    }*/

    public void setWeatherData(List<Weather> weather) {
        this.weatherData = weatherData;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(Double feels_like) {
        this.feels_like = feels_like;
    }

    public Double getMin_temperature() {
        return min_temperature;
    }

    public void setMin_temperature(Double min_temperature) {
        this.min_temperature = min_temperature;
    }

    public Double getMax_temperature() {
        return max_temperature;
    }

    public void setMax_temperature(Double max_temperature) {
        this.max_temperature = max_temperature;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public WindResponse getWind() {
        return wind;
    }

    public void setWind(WindResponse wind) {
        this.wind = wind;
    }

    public SunSetAndSunRise getSunSetAndSunRise() {
        return sunSetAndSunRise;
    }

    public void setSunSetAndSunRise(Date sunset, Date sunrise) {
        SunSetAndSunRise sunSetAndSunRise = new SunSetAndSunRise(sunrise, sunset);
        this.sunSetAndSunRise = sunSetAndSunRise;
    }
}

@Data
class Details {
    private Long humidity;
    private Long pressure;
    private Long visibility;
}

@Data
class WindResponse {
    private Double speed;
    private Double degree;
}

@Data
class SunSetAndSunRise {
    private String sunrise;
    private String sunset;
    private String length;
    private String remaining;

    @JsonIgnore
    private static final String pattern = "dd MM yyyy HH:mm:ss";
    @JsonIgnore
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public SunSetAndSunRise(Date sunrise, Date sunset) {
        setSunrise(getSimpleDateFormat().format(sunrise));
        setSunset(getSimpleDateFormat().format(sunset));
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }
}

class WeatherData {
    private String info;
    private String description;
    private String weather_icon;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return weather_icon;
    }

    public void setIcon(String weather_icon) {
        this.weather_icon = weather_icon;
    }
}