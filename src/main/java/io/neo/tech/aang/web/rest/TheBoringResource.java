package io.neo.tech.aang.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
public class TheBoringResource {

    @GetMapping("/ping")
    public ResponseEntity<?> doGet(){
        return ResponseEntity.ok().build();
    }
}
