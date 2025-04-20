package com.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/api/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("API response");
    }
}
