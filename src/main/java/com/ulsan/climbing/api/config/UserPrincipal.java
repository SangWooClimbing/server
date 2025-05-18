package com.ulsan.climbing.api.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.List;

public class UserPrincipal extends User {

    private final Long userId;

    public UserPrincipal(com.ulsan.climbing.api.domain.User user) {
        super(user.getEmail(), user.getPassword(),
                List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN")
                ));
        this.userId = user.getId();
    }

    public Long getUserId() {
        return userId;
    }
}
