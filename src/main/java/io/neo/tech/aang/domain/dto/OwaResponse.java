package io.neo.tech.aang.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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

    public Date getSunrise() {
        return getSys().getSunrise();
    }

    public Date getSunset() {
        return getSys().getSunset();
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

    /**
     * Getters of Main
     * @return
     */
    public Double getTemp() {
        return getMain().getTemp();
    }

    public Double getFeels_like() {
        return getMain().getFeels_like();
    }

    public Double getTemp_min() {
        return getMain().getTemp_min();
    }

    public Double getTemp_max() {
        return getMain().getTemp_max();
    }

    public Double getPressure() {
        return getMain().getPressure();
    }

    public Double getHumidity() {
        return getMain().getHumidity();
    }

    /**
     * Getters of Weather
     * @return
     */

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
    //"sea_level":1012,"grnd_level":932
    private Long sea_level;
    private Long grnd_level;

    public Long getSea_level() {
        return sea_level;
    }

    public void setSea_level(Long sea_level) {
        this.sea_level = sea_level;
    }

    public Long getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(Long grnd_level) {
        this.grnd_level = grnd_level;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(Double feels_like) {
        this.feels_like = feels_like;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
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

    public Date getSunrise() {
        return sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = new Date(sunrise * 1000);
    }

    public void setSunset(Long sunset) {
        this.sunset = new Date(sunset * 1000);
    }
}