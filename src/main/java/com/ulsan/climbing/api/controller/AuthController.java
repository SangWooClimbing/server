package com.ulsan.climbing.api.controller;

import com.ulsan.climbing.api.dto.request.Signup;
import com.ulsan.climbing.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/auth/signup")
    public void signup(@RequestBody Signup signup) {
        authService.signup(signup);
    }
}
