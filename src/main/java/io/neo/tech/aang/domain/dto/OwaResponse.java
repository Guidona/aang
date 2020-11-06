package io.neo.tech.aang.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OwaResponse {

    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private Double visibility;
    private Wind wind;
    private Clouds clouds;
    private Date dt;
    private Sys sys;
    private Long timezone;
    private Long id;
    private String name;
    private Long cod;

    public OwaResponse(){
    }

    public OwaResponse(Coord coord, List<Weather> weather, String base, Main main, Double visibility, Wind wind, Clouds clouds, Date dt, Sys sys, Long timezone, Long id, String name, Long cod) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.timezone = timezone;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = new Date(dt * 1000);
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Long getTimezone() {
        return timezone;
    }

    public void setTimezone(Long timezone) {
        this.timezone = timezone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "OwaResponse{" +
                "coord=" + coord +
                ", weather=" + weather +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", timezone=" + timezone +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }
}

@Data
class Coord {
    private Double lon;
    private Double lat;
}

@Data
class Weather {
    private Long id;
    private String main;
    private String description;
    private String icon;
}

@Data
class Main {
    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private Double pressure;
    private Double humidity;
}

@Data
class Wind {
    private Double speed;
    private Double deg;
}

@Data
class Clouds {
    private Double all;
}

@Data
class Sys {
    private Long type;
    private Long id;
    private String country;
    private Date sunrise;
    private Date sunset;

    public void setSunrise(Long sunrise) {
        this.sunrise = new Date(sunrise * 1000);
    }

    public void setSunset(Long sunset) {
        this.sunset = new Date(sunset * 1000);
    }
}