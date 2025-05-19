package com.ulsan.climbing.api.dto.response;

import com.ulsan.climbing.api.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {
    private Long id;
    private String email;
    private String name;

    @Builder
    public UserResponse(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
