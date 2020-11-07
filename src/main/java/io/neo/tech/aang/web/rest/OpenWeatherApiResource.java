package io.neo.tech.aang.web.rest;

import io.neo.tech.aang.domain.dto.OwaRequest;
import io.neo.tech.aang.domain.dto.OwaResponse;
import io.neo.tech.aang.domain.dto.ResponseData;
import io.neo.tech.aang.service.OpenWeatherApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api
@Slf4j
public class OpenWeatherApiResource {

    @Autowired
    private OpenWeatherApiService openWeatherApiService;

    @Value("${owa.image.url}")
    String imageUrl;

    @GetMapping("/weather/owa")
    public ResponseEntity<?> doGetOpenWeatherApi(@ApiParam(name = "city") @RequestParam(required = false) String city,
                                                 @ApiParam(name = "language") @RequestParam(required = false) String language,
                                                 @ApiParam(name = "unit") @RequestParam(required = false) String unit,
                                                 @ApiParam(name = "longitude") @RequestParam(required = false) Double longitude,
                                                 @ApiParam(name = "latitude") @RequestParam(required = false) Double latitude) {

        /**
         * Building request body
         */
        OwaRequest request = new OwaRequest();
        request.setCity(Optional.ofNullable(city));
        request.setLanguage(Optional.ofNullable(language));
        request.setUnit(Optional.ofNullable(unit));
        request.setLongitude(Optional.ofNullable(longitude));
        request.setLatitude(Optional.ofNullable(latitude));

        log.info("Getting weather information for request {}", request.toString());

        ResponseData response = openWeatherApiService.getWeatherInfo(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/weather/owa/{image}")
    public ResponseEntity<Map<String, String>> doGetImage(@PathVariable("image") String image,
                                                          HttpServletRequest request,
                                                          HttpServletResponse response) {

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(imageUrl)
                .buildAndExpand(image);

        Map<String, String> res = new HashMap<>();
        res.put("image_url", uri.toUriString());

        return ResponseEntity.status(HttpStatus.OK).body(res);

    }
}
