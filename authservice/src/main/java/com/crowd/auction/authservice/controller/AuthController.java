package com.crowd.auction.authservice.controller;

import com.crowd.auction.authservice.dto.AuthResponse;
import com.crowd.auction.authservice.dto.RegisterRequest;
import com.crowd.auction.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthController.API_BASE_URL)
@RequiredArgsConstructor
public class AuthController {

    public static final String API_BASE_URL = "/api/v1/auth";

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }
}
