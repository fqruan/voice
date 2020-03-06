package com.orange.voice.service;

import com.orange.voice.constant.MobileConstant;
import com.orange.voice.constant.UserConstant;
import com.orange.voice.util.RedisUtil;
import com.orange.voice.util.SecurityUtil;
import com.orange.voice.util.VerifyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AudioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AudioService.class);

    private String startFlag = MobileConstant.BAD_REQUEST;
    private String endFlag = MobileConstant.BAD_REQUEST;
    private boolean verifyRes = false;

    public void setStartFlagTrue() {
        startFlag = MobileConstant.TRUE_START;
    }

    public void setStartFlagFalse() {
        startFlag = MobileConstant.FALSE_START;
    }

    public void setEndFlagTrue() {
        endFlag = MobileConstant.TRUE_END;
    }

    public void setEndFlagFalse() {
        endFlag = MobileConstant.FALSE_END;
    }

    public String getStartFlag() {
        return startFlag;
    }

    public String getEndFlag() {
        return endFlag;
    }

    public String verify(int userId, String mobileId, String verifyStr) {
        String webIp = (String) RedisUtil.get(userId + "ip");
        String systemTime = (String)RedisUtil.get(userId + "time");
        String randomStr = (String)RedisUtil.get(userId + "random");

        String verifyStr2 = VerifyUtil.getVerifyString(userId, webIp, mobileId, systemTime, randomStr);
        verifyStr = SecurityUtil.decoderBase64(verifyStr);

        if (isChecked(verifyStr, verifyStr2)) {
            verifyRes = true;
            return UserConstant.LOGIN_SUCCESS;
        }

        verifyRes = false;
        return UserConstant.LOGIN_FAILED;
    }

    public boolean getVerifyRes() {
        boolean res = verifyRes;
        verifyRes = false;
        return res;
    }

    private boolean isChecked(String s1, String s2) {
        int l = s1.length();
        int c = 0;
        for (int i = 0; i < l; i++) {
            if (s1.charAt(i) == s2.charAt(i)) c++;
        }

        return c * 2 >= l;
    }
}
