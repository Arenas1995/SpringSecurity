package com.example.springsecurityjwt.controllers;

import com.example.springsecurityjwt.requests.AuthCreateUserRequest;
import com.example.springsecurityjwt.requests.AuthLoginRequest;
import com.example.springsecurityjwt.responses.AuthResponse;
import com.example.springsecurityjwt.services.AuthenticationServiceImpl;
import com.example.springsecurityjwt.services.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    private final UserDetailsServiceImpl userDetailsService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService, UserDetailsServiceImpl userDetailsService) {
        this.authenticationService = authenticationService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest userRequest){
        return new ResponseEntity<>(userDetailsService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(authenticationService.loginUser(userRequest), HttpStatus.OK);
    }
}