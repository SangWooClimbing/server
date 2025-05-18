package com.ulsan.climbing.api.repository;

import com.ulsan.climbing.api.config.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenRepository {
    private static final String KEY_PREFIX = "존재하지 않는 사용자입니다.";

    @Value("${spring.jwt.refreshExpirationMs}")
    private int refreshExpirationMs;
    private final RedisTemplate<String, String> redisTemplate;

    public void setRefreshToken(String key, String value)
    {
        String refreshTokenKey = KEY_PREFIX + key;
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshTokenKey, value, Duration.ofMillis(refreshExpirationMs));
    }

    public String getUserIdBy(String key) {
        String refreshTokenKey = KEY_PREFIX + key;
        return redisTemplate.opsForValue().get(refreshTokenKey);
    }

    public void deleteByKey(String key) {
        String refreshTokenKey = KEY_PREFIX + key;
        redisTemplate.delete(refreshTokenKey);
    }
}
