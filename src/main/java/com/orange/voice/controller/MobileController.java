package com.orange.voice.controller;

import com.orange.voice.service.AudioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mobile")
public class MobileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MobileController.class);

    @Autowired
    private AudioService audioService;

    @ResponseBody
    @RequestMapping("/start")
    public String getStartFlag() {
        return audioService.getStartFlag();
    }

    @ResponseBody
    @RequestMapping("/end")
    public String getEndFlag() {
        return audioService.getEndFlag();
    }
}
