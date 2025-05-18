package com.ulsan.climbing.api.dto.response;

import com.ulsan.climbing.api.domain.User;

public class UserResponse {
    private Long id;
    private String email;
    private String name;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
