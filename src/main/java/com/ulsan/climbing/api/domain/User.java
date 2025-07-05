package com.ulsan.climbing.api.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User extends BaseEntity {
    @Column(length = 50, nullable = false)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(length = 10,nullable = false)
    private String name;

    @Column(length = 50,nullable = true)
    private String provider;

    @Column(length = 50,nullable = true)
    private String providerId;

    @Builder
    public User(String email, String password, String name, String provider, String providerId) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.provider = provider;
        this.providerId = providerId;
    }
}
