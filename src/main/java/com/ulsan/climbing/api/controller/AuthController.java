package com.ulsan.climbing.api.controller;

import com.ulsan.climbing.api.common.ApiResponse;
import com.ulsan.climbing.api.dto.request.*;
import com.ulsan.climbing.api.dto.response.TokenResponse;
import com.ulsan.climbing.api.service.AuthService;
import com.ulsan.climbing.api.service.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private final AuthService authService;
    private final OauthService oauthService;

    @PostMapping("/signup")
    public ApiResponse<?> signup(@RequestBody Signup signup) {
        log.info("Signup 요청 들어옴: {}", signup);
        authService.signup(signup);
        return ApiResponse
                .ok(signup);
    }

    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody Login login) {
        return ApiResponse.ok(authService.login(login));
    }

    @PostMapping("/refresh")
    public ApiResponse<TokenResponse> login(@RequestBody Refresh refresh) {
        return ApiResponse.ok(authService.refresh(refresh));
    }

    @PostMapping("/google")
    public ApiResponse<?> loginWithGoogle(@RequestBody OauthTokenRequest request) {
        OauthInfoRequest oauthUsr
                = oauthService.getGoogleUser(request.getAccessToken());
        return ApiResponse.ok(authService.oauthLogin(oauthUsr, "google"));
    }
}
