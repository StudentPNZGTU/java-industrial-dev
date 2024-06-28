package org.penzgtu.Application.controller;

import org.penzgtu.Application.dto.AuthRequest;
import org.penzgtu.Application.dto.AuthResponse;
import org.penzgtu.Application.dto.RegisterRequest;
import org.penzgtu.Application.dto.RegisterResponse;
import org.penzgtu.Application.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
}
