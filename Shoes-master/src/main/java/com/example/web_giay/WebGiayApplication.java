package com.example.web_giay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class WebGiayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebGiayApplication.class, args);
    }

}
