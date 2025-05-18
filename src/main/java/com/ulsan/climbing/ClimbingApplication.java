package com.ulsan.climbing;

import com.ulsan.climbing.api.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
@EnableJpaAuditing
public class ClimbingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClimbingApplication.class, args);
    }

}
