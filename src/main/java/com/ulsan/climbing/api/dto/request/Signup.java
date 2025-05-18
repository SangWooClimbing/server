package com.ulsan.climbing.api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Signup {
    private String email;
    private String password;
    private String name;
}
