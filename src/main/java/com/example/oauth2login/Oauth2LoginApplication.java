package com.example.oauth2login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Oauth2LoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2LoginApplication.class, args);
    }

}
