package com.ulsan.climbing.api.controller;

import com.ulsan.climbing.api.common.ApiResponse;
import com.ulsan.climbing.api.config.UserPrincipal;
import com.ulsan.climbing.api.dto.response.UserResponse;
import com.ulsan.climbing.api.exception.UnAuthorizedException;
import com.ulsan.climbing.api.exception.UserNotFound;
import com.ulsan.climbing.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ApiResponse<UserResponse> getMe(@AuthenticationPrincipal Long userId) {
        if (userId == null) {
            throw new UnAuthorizedException();
        }

        return ApiResponse.ok(userService.getUserProfile(userId));
    }
}
