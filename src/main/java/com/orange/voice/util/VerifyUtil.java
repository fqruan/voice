package com.orange.voice.util;

public class VerifyUtil {

    public static String getVerifyString(int userId, String webIP, String mobileId) {
        String randomStr = RandomUtil.getRandomString(20);
        long systemTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(userId);
        sb.append(webIP);
        sb.append(mobileId);
        sb.append(randomStr);
        sb.append(systemTime);

        RedisUtil.set(userId + "time", String.valueOf(systemTime));
        RedisUtil.set(userId + "random", String.valueOf(systemTime));

        return SecurityUtil.getMD5(sb.toString());
    }

    public static String getVerifyString(int userId, String webIP, String mobileId, String systemTime, String randomStr) {
        StringBuilder sb = new StringBuilder();
        sb.append(userId);
        sb.append(webIP);
        sb.append(mobileId);
        sb.append(randomStr);
        sb.append(systemTime);

        return SecurityUtil.getMD5(sb.toString());
    }
}
