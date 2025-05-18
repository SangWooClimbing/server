package com.ulsan.climbing.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisUtils {
    private final RedisTemplate<String, String> redisTemplate;
    @Value("${spring.jwt.refreshExpirationMs}")
    private int refreshExpirationMs;

    public void setRefreshToken(String key, String value)
    {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        deleteByValue(value);
        valueOperations.set(key, value, Duration.ofMillis(refreshExpirationMs));
    }

    public String getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

    public boolean isExist(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void deleteByValue(String value) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();

        redisTemplate.keys("*").forEach(key -> {
            String storedValue = valueOps.get(key);
            if (value.equals(storedValue)) {
                redisTemplate.delete(key);
            }
        });
    }
}
