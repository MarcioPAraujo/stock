package edu.marcio.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping
    public ResponseEntity<String> getTest() {
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

}
