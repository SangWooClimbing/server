package com.ulsan.climbing.api.service;

import com.ulsan.climbing.api.config.JwtUtils;
import com.ulsan.climbing.api.domain.User;
import com.ulsan.climbing.api.dto.request.Login;
import com.ulsan.climbing.api.dto.request.OauthInfoRequest;
import com.ulsan.climbing.api.dto.request.Refresh;
import com.ulsan.climbing.api.dto.request.Signup;
import com.ulsan.climbing.api.dto.response.TokenResponse;
import com.ulsan.climbing.api.exception.AlreadyExistsEmailException;
import com.ulsan.climbing.api.exception.UserNotFound;
import com.ulsan.climbing.api.repository.TokenRepository;
import com.ulsan.climbing.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public void signup(Signup signup) {
        Optional<User> userOptional = userRepository.findByEmail(signup.getEmail());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        String encryptedPassword = passwordEncoder.encode(signup.getPassword());

        var user = User.builder()
                .email(signup.getEmail())
                .password(encryptedPassword)
                .name(signup.getName())
                .build();
        userRepository.save(user);
    }

    public TokenResponse login(Login login) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
        );

        User user = userRepository.findByEmail(login.getEmail()).orElseThrow(
                UserNotFound::new
        );

        String accessToken = jwtUtils.generateAccessToken(user.getId());
        String refreshToken = jwtUtils.generateRefreshToken(user.getId());
        tokenRepository.setRefreshToken(refreshToken, user.getId().toString());

        log.info("Refresh token: {}", refreshToken);

        return new TokenResponse(accessToken, refreshToken);
    }

    public TokenResponse oauthLogin(OauthInfoRequest request, String provider) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(
                User.builder()
                        .email(request.getEmail())
                        .name(request.getName())
                        .provider(provider)
                        .build()
        );
        log.info("Refresh token: {}", user);
        userRepository.save(user);

        String accessToken = jwtUtils.generateAccessToken(user.getId());
        String refreshToken = jwtUtils.generateRefreshToken(user.getId());
        tokenRepository.setRefreshToken(refreshToken, user.getId().toString());

        return new TokenResponse(accessToken, refreshToken);
    }

    public TokenResponse refresh(Refresh req) {
        jwtUtils.validateRefreshToken(req.getRefreshToken());

        Long userId = jwtUtils.getUserIdFromJwt(req.getRefreshToken());
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFound::new
        );
        tokenRepository.getUserIdBy(req.getRefreshToken());

        String accessToken = jwtUtils.generateAccessToken(user.getId());
        String refreshToken = jwtUtils.generateRefreshToken(user.getId());

        tokenRepository.setRefreshToken(refreshToken, user.getId().toString());
        tokenRepository.deleteByKey(req.getRefreshToken());

        return new TokenResponse(accessToken, refreshToken);
    }
}
