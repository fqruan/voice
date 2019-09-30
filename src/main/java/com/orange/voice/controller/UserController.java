package com.orange.voice.controller;

import com.orange.voice.bean.User;
import com.orange.voice.dao.UserMapper;
import com.orange.voice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/list")
    public List<User> list() {
        return userService.getList();
    }
}
