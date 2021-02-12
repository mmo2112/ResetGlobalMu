package com.global.mu.service;

import java.math.BigInteger;
import java.util.Random;

public class CookieService {
    static final int COOKIE_LENGTH = 40;
    static String prefix = "DmNCMSSession=";

    private String init() {
        Random random = new Random();
        BigInteger bigInteger = new BigInteger(160, random);
        String hex = bigInteger.toString(16);

        if (hex.length() < COOKIE_LENGTH) {
            int count = COOKIE_LENGTH - hex.length();
            String addingZero = "";

            for (int i = 0; i < count; i++) {
                addingZero += "0";
            }

            hex = addingZero + prefix;
        } else if (hex.length() > COOKIE_LENGTH) {
            hex = hex.substring(0, COOKIE_LENGTH);
        }

        String result = prefix + hex;
        System.out.println("fake cookie: " + result);
        return result;
    }
}
