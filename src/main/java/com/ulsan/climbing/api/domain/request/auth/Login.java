package com.ulsan.climbing.api.domain.request.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Login {
    private String email;
    private String password;
}
