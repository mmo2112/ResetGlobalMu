package com.global.mu.common;

public class CacheUtils {
    public static String getRedisKey(String username) {
        return String.format("%s_%s", Constant.COOKIE_KEY, username);
    }
}
