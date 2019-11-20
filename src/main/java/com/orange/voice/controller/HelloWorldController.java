package com.orange.voice.controller;

import com.orange.voice.util.IpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {

    @ResponseBody
    @RequestMapping("/hw")
    public String hello() {
        return "hello world";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "/hello";
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpServletRequest request) {
        String ip = IpUtil.getIpAddr(request);
        return ip;
    }
}
