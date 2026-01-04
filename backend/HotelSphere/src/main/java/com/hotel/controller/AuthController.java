package com.hotel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.JwtResponseDTO;
import com.hotel.dto.LoginRequestDTO;
import com.hotel.dto.RegisterRequestDTO;
import com.hotel.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponseDTO> register(
            @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(
                new JwtResponseDTO(authService.register(request))
        );
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(
            @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(
                new JwtResponseDTO(authService.login(request))
        );
    }
}
