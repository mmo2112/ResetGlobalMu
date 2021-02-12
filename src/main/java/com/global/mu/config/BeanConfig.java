package com.global.mu.config;

import com.global.mu.buz.GrandResetBuzImpl;
import com.global.mu.buz.LoginBuz;
import com.global.mu.buz.ResetBuzImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ResetBuzImpl resetBuz() {
        return new ResetBuzImpl();
    }

    @Bean
    public GrandResetBuzImpl grandResetBuz() {
        return new GrandResetBuzImpl();
    }

    @Bean
    public LoginBuz loginBuz() {
        return new LoginBuz();
    }
}
