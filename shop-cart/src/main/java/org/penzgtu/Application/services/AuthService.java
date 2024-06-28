package org.penzgtu.Application.services;

import org.penzgtu.Application.dto.AuthRequest;
import org.penzgtu.Application.dto.AuthResponse;
import org.penzgtu.Application.dto.RegisterRequest;
import org.penzgtu.Application.dto.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Service
public class AuthService {

    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AuthService(RestTemplate restTemplate, UserService userService, PasswordEncoder passwordEncoder) {
        this.restTemplate = restTemplate;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    /*
        Вход в систему FakeStoreAPI для получения токена.
        Пользователей на сервере на текущий момент: 10  (Столько дает API)
        Токен выдается если пользователь существует
     */

    public AuthResponse login(AuthRequest authRequest) {
        ResponseEntity<AuthResponse> response;
        long id = userService.getUserIdByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword());
        try {
            String url = "https://fakestoreapi.com/auth/login";
            HttpEntity<AuthRequest> request = new HttpEntity<>(authRequest);
            response = restTemplate.exchange(
                    url, HttpMethod.POST, request, AuthResponse.class
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Success login. Your token:\n" + response.getBody().getToken());
            }
            // замена на id
            response.getBody().setToken(String.valueOf(id));
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Failed to login (API)");
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(String.valueOf(id));
            return authResponse;
        }
    }
    /*
        На данный момент API выводит id: 11
     */
    public RegisterResponse register(RegisterRequest registerRequest) {
        String url = "https://fakestoreapi.com/users";
        registerRequest.getUser().setPassword(passwordEncoder.encode(
                registerRequest.getUser().getPassword()));
        HttpEntity<RegisterRequest> request = new HttpEntity<>(registerRequest);
        ResponseEntity<RegisterResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, request, RegisterResponse.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("Success login. Your id:\n" + response.getBody().getMessage());
        } else {System.err.println("Failed to register (API)");}
        // замена на id
        response.getBody().setMessage(String.valueOf(userService.getLastId()));
        return response.getBody();
    }
}

