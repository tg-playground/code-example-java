package com.taogen.controller;

import com.taogen.entity.Input;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ValidateController
{
    @PostMapping("/validateRequestBody")
    ResponseEntity<String> validateRequestBody(@Valid @RequestBody Input input) {
        System.out.println("input: " + input);
        return ResponseEntity.ok("valid");
    }
}
