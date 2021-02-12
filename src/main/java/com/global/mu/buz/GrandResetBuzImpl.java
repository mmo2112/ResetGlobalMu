package com.global.mu.buz;

import com.global.mu.buz.bo.Account;

import com.global.mu.common.CacheUtils;
import com.global.mu.common.Constant;
import com.global.mu.common.HttpUtils;
import com.global.mu.common.MethodEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GrandResetBuzImpl implements MuBuz {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    LoginBuz loginBuz;

    public String run(Account account) throws IOException, InterruptedException {
        int maxRetryTime = 3;
        String user = account.getUsername();
        String cookie = (String) redisTemplate.opsForValue().get(CacheUtils.getRedisKey(account.getUsername()));
        System.out.println("Prepare to grand reset " + user + " with cookie " + cookie);

        if (StringUtils.isEmpty(cookie))
            throw new RuntimeException("Can not grand reset due to cookie empty");

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("player", account.getId());
        String result = HttpUtils.callApi(user, MethodEnum.POST, Constant.GRAND_RESET, cookie, dataMap);

        //{"error":"You don't have permission to access this page. Please login."}
        if (result.contains("login")) {
            for (int i = 0; i < maxRetryTime; i++) {
                result = loginBuz.run(account);
                System.out.println("Try to re login " + user + ": " + result);

                if (result.equals("success")) {
                    result = HttpUtils.callApi(user, MethodEnum.POST, Constant.GRAND_RESET, cookie, dataMap);

                    if (!StringUtils.isEmpty(result) && !result.contains("login"))
                        break;
                    else if (i == maxRetryTime - 1)
                        throw new RuntimeException("Grand reset " + user + " got maximum retry exceeded");
                    else
                        Thread.sleep(200L);
                } else {
                    System.out.println("Re login for " + user + " failed");
                }
            }
        }

        return result;
    }
}
