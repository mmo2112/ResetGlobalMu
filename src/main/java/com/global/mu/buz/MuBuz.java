package com.global.mu.buz;

import com.global.mu.buz.bo.Account;

import java.io.IOException;

public interface MuBuz {
    String run(Account account) throws IOException, InterruptedException;
}
