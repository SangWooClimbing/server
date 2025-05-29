package com.ulsan.climbing.api.service;

import com.ulsan.climbing.api.domain.User;
import com.ulsan.climbing.api.dto.response.UserResponse;
import com.ulsan.climbing.api.exception.AlreadyExistsEmailException;
import com.ulsan.climbing.api.exception.UserNotFound;
import com.ulsan.climbing.api.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("내정보 조회 성공")
    void test1() {
        // given
        User user = User.builder()
                .email("test@ulsan.com")
                .password("123456")
                .name("Test")
                .build();
        userRepository.save(user);

        // when
        UserResponse result = userService.getUserProfile(user.getId());

        // then
        assertEquals("test@ulsan.com", user.getEmail());
        assertEquals("123456", user.getPassword());
        assertEquals("Test", user.getName());
    }

    @Test
    @DisplayName("존재하지 않은 유저 조회")
    void test2() {
        // given
        // when
        // then
        assertThrows(UserNotFound.class, () -> userService.getUserProfile(1L));
    }
}
