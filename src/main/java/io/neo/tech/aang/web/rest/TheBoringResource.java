package io.neo.tech.aang.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheBoringResource {

    @GetMapping("/ping")
    public ResponseEntity<?> doGet(){
        return ResponseEntity.ok().build();
    }
}
