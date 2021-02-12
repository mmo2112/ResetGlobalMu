package com.global.mu.buz;

import com.global.mu.buz.bo.Account;
import com.global.mu.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginBuz implements MuBuz {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public String run(Account account) throws InterruptedException {
        int maxRetryTime = 9;
        String result = null;
        String cookie = CookieUtils.getCookie();

        for (int i = 0; i < maxRetryTime; i++) {
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("username", account.getUsername());
            dataMap.put("password", account.getPassword());

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                result = HttpUtils.callApi(account.getUsername(), MethodEnum.POST, Constant.LOGIN, cookie, dataMap);
                redisTemplate.opsForValue().set(CacheUtils.getRedisKey(account.getUsername()), cookie);
                System.out.println("Success cache " + account.getUsername() + " cookie at " + i + " times: " + dateFormat.format(date));
            } catch (Exception e) {
                System.out.println("Exception " + account.getUsername() + " happen: " + e.getMessage());
                e.printStackTrace();
                Thread.sleep(500L);
            }

            if (!StringUtils.isEmpty(result))
                break;
            else if (i == maxRetryTime - 1)
                throw new RuntimeException("After retry " + maxRetryTime + " still can not sign in");
        }

        return "success";
    }
}
