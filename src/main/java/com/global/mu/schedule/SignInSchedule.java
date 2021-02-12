package com.global.mu.schedule;

import com.global.mu.buz.LoginBuz;
import com.global.mu.buz.bo.Account;
import com.global.mu.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class SignInSchedule {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    LoginBuz loginBuz;

    @Resource
    Map<String, Account> accountMap;

    @Scheduled(fixedDelayString = "180000") //3 min
    public void run() throws InterruptedException, IOException {
        for (String k : accountMap.keySet())
            loginBuz.run(accountMap.get(k));
    }
}
