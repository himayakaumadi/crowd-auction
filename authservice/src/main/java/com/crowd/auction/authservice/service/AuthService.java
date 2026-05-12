package com.crowd.auction.authservice.service;

import com.crowd.auction.authservice.dto.AuthResponse;
import com.crowd.auction.authservice.dto.RegisterRequest;
import com.crowd.auction.authservice.model.Role;
import com.crowd.auction.authservice.model.User;
import com.crowd.auction.authservice.repository.UserRepository;
import com.crowd.auction.authservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole() : Role.BUYER)
                .build();
        
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
