package com.global.mu.controller;

import com.global.mu.buz.GrandResetBuzImpl;
import com.global.mu.buz.ResetBuzImpl;
import com.global.mu.buz.bo.Account;
import com.global.mu.param.AccountParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController("MuController")
@RequestMapping("/mu")
public class MuController {
    @Autowired
    ResetBuzImpl resetBuz;

    @Autowired
    GrandResetBuzImpl grandResetBuz;

    @Resource
    Map<String, Account> accountMap;

    @PostMapping("/reset")
    public String reset(@RequestBody AccountParam accountParam) {
        if (!accountMap.containsKey(accountParam.getUsername()))
            throw new RuntimeException("Can not find account " + accountParam.getUsername());

        String result;

        try {
            result = resetBuz.run(accountMap.get(accountParam.getUsername()));
        } catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }

    @PostMapping("/greset")
    public String greset(@RequestBody AccountParam accountParam) {
        if (!accountMap.containsKey(accountParam.getUsername()))
            throw new RuntimeException("Can not find account " + accountParam.getUsername());

        String result;

        try {
            result = grandResetBuz.run(accountMap.get(accountParam.getUsername()));
        } catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }
}
