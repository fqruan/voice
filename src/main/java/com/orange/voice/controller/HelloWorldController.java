package com.orange.voice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
