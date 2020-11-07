package io.neo.tech.aang.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.neo.tech.aang.domain.dto.OwaRequest;
import io.neo.tech.aang.domain.dto.OwaResponse;
import io.neo.tech.aang.domain.dto.ResponseData;
import io.neo.tech.aang.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

@Service
@Slf4j
public class OpenWeatherApiService {

    @Value("${owa.api.url}")
    public String url;
    @Value("${owa.api.id}")
    public String appid;
    @Value("${owa.image.url}")
    public String imageUrl;

    public ResponseData getWeatherInfo(OwaRequest request) {
        log.info("Getting weather information for OWA at url {} with id {}", url, appid);
        /**
         * TODO
         * 1. Build url with parameters to call API
         * 2. Do call
         * 3. Process response and
         * 4. Do return
         */
        Map<String, Object> params = new HashMap<>();
        // API security configuration
        params.put("appid", appid);

        buildParams(params, request);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

        params.forEach(uriBuilder::queryParam);

        Consumer<OkHttpUtils> instance = t -> {
            t.setUrl(uriBuilder.toUriString());
            t.setHttpMethod(OkHttpUtils.HttpMethod.GET);
        };

        try {
            Response response = OkHttpUtils.doGet(instance);
            log.info(response.message());

            ObjectMapper mapper = new ObjectMapper();
            OwaResponse owaResponse = mapper.readValue(response.body().string(), OwaResponse.class);
            log.info(owaResponse.toString());
            return buildResponseDataFromOwaResponse(owaResponse);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return new ResponseData();
        }
    }

    void buildParams(Map<String, Object> params, OwaRequest request) {
        request.getCity().ifPresent(c -> params.put("q", c));
        request.getLanguage().ifPresent(l -> params.put("lang", l));
        request.getLatitude().ifPresent(lat -> params.put("lat", lat));
        request.getLongitude().ifPresent(lon -> params.put("lan", lon));
        request.getUnit().ifPresent(unit -> params.put("unit", unit));
    }

    ResponseData buildResponseDataFromOwaResponse(OwaResponse response) {
        ResponseData responseData = new ResponseData();
        responseData.setTemperature(response.getTemp());
        responseData.setFeels_like(response.getFeels_like());
        responseData.setMin_temperature(response.getTemp_min());
        responseData.setMax_temperature(response.getTemp_max());

        responseData.setWeatherData(response.getWeather());

        responseData.setSunSetAndSunRise(response.getSunrise(), response.getSunset());
        return responseData;
    }
}
