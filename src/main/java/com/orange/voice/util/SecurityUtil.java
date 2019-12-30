package com.orange.voice.util;

import org.springframework.util.DigestUtils;

public class SecurityUtil {

    //Encrypt and decrypt

    //slat，used to mix md5
    private static final String slat = "=&%0525***&&%%$$#@--=";

    /**
     * md5
     * @param str
     * @return str
     */
    public static String getMD5(String str) {
        String base = str +"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
