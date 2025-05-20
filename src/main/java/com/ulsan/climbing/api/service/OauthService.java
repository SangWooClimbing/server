package com.ulsan.climbing.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ulsan.climbing.api.dto.request.OauthInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OauthService {
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    String clientId;

    // 토큰에서 Google 사용자 정보를 추출합니다.
    public OauthInfoRequest getGoogleUser(String accessToken) {
        String userInfoEndpoint = "https://www.googleapis.com/oauth2/v2/userinfo";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(userInfoEndpoint, HttpMethod.GET, entity, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.getBody(), OauthInfoRequest.class);
        } catch (Exception e) {
            throw new RuntimeException("Google 유저 정보 파싱 실패", e);
        }
    }
}
