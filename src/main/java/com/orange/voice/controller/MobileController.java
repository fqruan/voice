package com.orange.voice.controller;

import com.orange.voice.constant.UserConstant;
import com.orange.voice.service.AudioService;
import com.orange.voice.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @RequestMapping(value = "/updateString", method = RequestMethod.GET)
    public String updateStr(HttpServletRequest request, HttpSession session) {
        String mobileId = request.getParameter("mobileId");
        String verifyStr = request.getParameter("verifyStr");
        int userId = Integer.parseInt(request.getParameter("userId"));
        String res = audioService.verify(userId, mobileId, verifyStr);

//        if (res.equals(UserConstant.LOGIN_SUCCESS)) {
//            session.setAttribute("res", userId);
//            return "redirect:/login/loginsuccess";
//        }
//
//        session.setAttribute("res", userId);
//        return "redirect:/login/loginfail";

        return "OK";
    }
}
