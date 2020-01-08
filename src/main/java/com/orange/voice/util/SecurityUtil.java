package com.orange.voice.util;

import com.orange.voice.controller.MobileController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

//Encrypt and decrypt
public class SecurityUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtil.class);

    //slat，used to mix md5
    private static final String slat = "=&%0525***&&%%$$#@--=";

    /**
     * md5
     *
     * @param str
     * @return str
     */
    public static String getMD5(String str) {
        String base = str + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    public static void decoderBase64File(String base64Code, String targetName) throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        LOGGER.debug(targetName);
        File file = new File(targetName);
        LOGGER.debug(String.valueOf(file.exists()));
        boolean res = file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        out.write(buffer);
        out.close();
    }
}
