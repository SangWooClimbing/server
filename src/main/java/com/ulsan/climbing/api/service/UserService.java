package com.ulsan.climbing.api.service;

import com.ulsan.climbing.api.domain.User;
import com.ulsan.climbing.api.dto.response.UserResponse;
import com.ulsan.climbing.api.exception.UserNotFound;
import com.ulsan.climbing.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        return UserResponse.of(user);
    }
}
