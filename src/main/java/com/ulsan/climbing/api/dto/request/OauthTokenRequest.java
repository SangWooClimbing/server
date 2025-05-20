package com.ulsan.climbing.api.dto.request;

import lombok.Data;

@Data
public class OauthTokenRequest {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
