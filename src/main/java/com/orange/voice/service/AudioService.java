package com.orange.voice.service;

import com.orange.voice.constant.MobileConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AudioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AudioService.class);

    private String startFlag = MobileConstant.BAD_REQUEST;
    private String endFlag = MobileConstant.BAD_REQUEST;

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
}
