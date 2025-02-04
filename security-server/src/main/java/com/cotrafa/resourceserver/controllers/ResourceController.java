package com.cotrafa.resourceserver.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
@Slf4j
public class ResourceController {

    @GetMapping("/user")
    public ResponseEntity<String> readUser(Authentication authentication) {
        log.info("The user can read." + authentication.getAuthorities());
        return ResponseEntity.ok("The user can read." + authentication.getAuthorities());
    }

    @PostMapping("/user")
    public ResponseEntity<String> writeUser(Authentication authentication) {
        log.info("The user can write." + authentication.getAuthorities());
        return ResponseEntity.ok("The user can write." + authentication.getAuthorities());
    }

}
