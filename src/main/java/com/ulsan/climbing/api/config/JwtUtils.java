package com.ulsan.climbing.api.config;

import com.ulsan.climbing.api.exception.UnValidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${spring.jwt.secret}")
    private String secret;
    @Value("${spring.jwt.accessExpirationMs}")
    private int accessExpirationMs;
    @Value("${spring.jwt.refreshExpirationMs}")
    private int refreshExpirationMs;

    public String generateAccessToken(Long userId) {
        return Jwts.builder()
                .claim("type", "refresh")
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + accessExpirationMs))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String generateRefreshToken(Long userId) {
        return Jwts.builder()
                .claim("type", "refresh")
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + refreshExpirationMs))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).build().parseSignedClaims(token).getBody();
        return Long.valueOf(claims.get("userId").toString());
    }

    public String getTypeFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).build().parseSignedClaims(token).getBody();
        return claims.get("type").toString();
    }

    public void validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).build().parseSignedClaims(token);
        } catch (Exception e) {
            throw new UnValidTokenException();
        }
    }

    public void validateRefreshToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).build().parseSignedClaims(token);
        } catch (Exception e) {
//            redisUtil.deleteData(token);
            throw new UnValidTokenException();
        }
    }
}
