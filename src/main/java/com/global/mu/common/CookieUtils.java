package com.global.mu.common;

import java.math.BigInteger;
import java.util.Random;

public class CookieUtils {
    static final int COOKIE_LENGTH = 40;
    static String prefix = "DmNCMSSession=";

    public static String getCookie() {
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

        return prefix + hex;
    }
}
