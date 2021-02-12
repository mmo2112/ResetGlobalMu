package com.global.mu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MuApp {
    public static void main(String[] args) {
        SpringApplication.run(MuApp.class, args);
    }
}