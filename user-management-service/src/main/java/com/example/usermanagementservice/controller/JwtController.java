package com.example.usermanagementservice.controller;

import com.example.usermanagementservice.models.JwtRequest;
import com.example.usermanagementservice.models.JwtResponse;
import com.example.usermanagementservice.services.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

    @GetMapping("/currentUser")
    public String getUserByJwtToken() {
        return jwtService.getUserByJwtToken();
    }
}
