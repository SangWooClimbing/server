package com.ulsan.climbing.api.domain.request.auth;

import lombok.Data;

@Data
public class OauthTokenRequest {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
