package com.global.mu.config;

import com.global.mu.buz.bo.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class AccountConfig {
    @Bean
    public Map<String, Account> accountMap() {
        Map<String, Account> accountMap = new LinkedHashMap<String, Account>() {{
            //Add account here
        }};

        return accountMap;
    }
}
