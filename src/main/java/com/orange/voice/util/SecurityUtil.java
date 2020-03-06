package com.orange.voice.util;

import com.orange.voice.controller.MobileController;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

//Encrypt and decrypt
public class SecurityUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtil.class);

    //slat，used to mix md5
    private static final String slat = "=&%0525***&&%%$$#@--=";

    private static KeyPairGenerator keyPairGenerator;
    private static KeyPair keyPair;
    private static RSAPublicKey rsaPublicKey;
    private static RSAPrivateKey rsaPrivateKey;
    private static PKCS8EncodedKeySpec pkcs8EncodedKeySpec;
    private static KeyFactory keyFactory;
    private static Cipher cipher;

    static {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            keyPair = keyPairGenerator.generateKeyPair();
            rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            cipher = Cipher.getInstance("RSA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //md5
    public static String getMD5(String str) {
        String base = str + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    //base64 encode
    public static String encodeBase64(String src) {
        return new BASE64Encoder().encode(src.getBytes());
    }

    //base64 decoder
    public static String decoderBase64(String src) {
        try {
            byte[] buffer = new BASE64Decoder().decodeBuffer(src);
            return new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    //base64 encode file
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    //base64 decoder file
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

    public static final String src = "rsa test";

    public static void jdkRSA(){
        try {
            // 1.初始化发送方密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("Public Key:" + Base64.encodeBase64String(rsaPublicKey.getEncoded()));
            System.out.println("Private Key:" + Base64.encodeBase64String(rsaPrivateKey.getEncoded()));

            // 2.私钥加密、公钥解密 ---- 加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("私钥加密、公钥解密 ---- 加密:" + Base64.encodeBase64String(result));

            // 3.私钥加密、公钥解密 ---- 解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(result);
            System.out.println("私钥加密、公钥解密 ---- 解密:" + new String(result));



            // 4.公钥加密、私钥解密 ---- 加密
            X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            KeyFactory keyFactory2 = KeyFactory.getInstance("RSA");
            PublicKey publicKey2 = keyFactory2.generatePublic(x509EncodedKeySpec2);
            Cipher cipher2 = Cipher.getInstance("RSA");
            cipher2.init(Cipher.ENCRYPT_MODE, publicKey2);
            byte[] result2 = cipher2.doFinal(src.getBytes());
            System.out.println("公钥加密、私钥解密 ---- 加密:" + Base64.encodeBase64String(result2));

            // 5.私钥解密、公钥加密 ---- 解密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec2 = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory5 = KeyFactory.getInstance("RSA");
            PrivateKey privateKey5 = keyFactory5.generatePrivate(pkcs8EncodedKeySpec2);
            Cipher cipher5 = Cipher.getInstance("RSA");
            cipher5.init(Cipher.DECRYPT_MODE, privateKey5);
            byte[] result5 = cipher5.doFinal(result2);
            System.out.println("公钥加密、私钥解密 ---- 解密:" + new String(result5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String privateEncode(String src) {
        String ans = "";
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(src.getBytes());
            ans = new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ans;
    }

    public static String publicDecode(String src) {
        String ans = "";
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            ans = new String(cipher.doFinal(src.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ans;
    }

    public static String publicEncode(String src) {
        String ans = "";
        try {
            X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            KeyFactory keyFactory2 = KeyFactory.getInstance("RSA");
            PublicKey publicKey2 = keyFactory2.generatePublic(x509EncodedKeySpec2);
            Cipher cipher2 = Cipher.getInstance("RSA");
            cipher2.init(Cipher.ENCRYPT_MODE, publicKey2);
            byte[] result2 = cipher2.doFinal(src.getBytes());
            ans = new String(result2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ans;
    }

    public static String privateDecode(String src) {
        String ans = "";
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec2 = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory5 = KeyFactory.getInstance("RSA");
            PrivateKey privateKey5 = keyFactory5.generatePrivate(pkcs8EncodedKeySpec2);
            Cipher cipher5 = Cipher.getInstance("RSA");
            cipher5.init(Cipher.DECRYPT_MODE, privateKey5);
            byte[] result5 = cipher5.doFinal(src.getBytes());
            ans = new String(result5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ans;
    }
}
