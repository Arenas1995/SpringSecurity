package com.example.springsecurityjwt.controllers;

import com.example.springsecurityjwt.enums.RoleEnum;
import com.example.springsecurityjwt.models.RoleEntity;
import com.example.springsecurityjwt.models.UserEntity;
import com.example.springsecurityjwt.repositories.UserRepository;
import com.example.springsecurityjwt.requests.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public PrincipalController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello not secured";
    }

    @GetMapping("/hello-secured")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String helloSecured() {
        return "Hello Secured";
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequest userRequest) {

        Set<RoleEntity> roles = userRequest.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(RoleEnum.valueOf(role)).build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(roles).build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/delete-user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestParam String id) {

        userRepository.deleteById(Long.parseLong(id));

        return ResponseEntity.ok(new HashMap<String, String>().put("message", "Usuario eliminado: " + id));
    }
}
