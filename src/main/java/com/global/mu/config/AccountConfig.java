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
            String password = "abcdef";
            put("2", new Account("mmo2112", password, "30069"));
            put("3", new Account("mmo2113", password, "38839"));
            put("1", new Account("mmo2111", password, "30142"));
            put("0", new Account("mmo2110", password, "30668"));
            put("-1", new Account("huychut", password, "35732"));
        }};

        return accountMap;
    }
}
