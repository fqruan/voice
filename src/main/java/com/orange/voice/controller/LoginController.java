package com.orange.voice.controller;

import com.orange.voice.bean.User;
import com.orange.voice.constant.UserConstant;
import com.orange.voice.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/loginJudge", method = RequestMethod.POST)
    public String loginJudge(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String loginRes = loginService.login(username, password);

        if (loginRes.equals(UserConstant.LOGIN_SUCCESS)) {
            session.setAttribute("res", username);
            return "redirect:/login/loginsuccess";
        }

        session.setAttribute("res", loginRes);
        return "redirect:/login/loginfail";
    }

    @RequestMapping(value = "/loginsuccess", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginSuccess() {
        return "/loginsuccess";
    }

    @RequestMapping(value = "/loginfail", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginFail() {
        return "/loginfail";
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    public String register() {
        return "/register";
    }

    @RequestMapping(value = "/registerJudge", method = RequestMethod.POST)
    public String registerJudge(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isVerify = request.getParameter("verify").equals("1");

        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        user.setVerify(isVerify);
        String res = loginService.register(user);

        if (res.equals(UserConstant.REGISTER_SUCCESS)) {
            return "redirect:/login/registersuccess";
        }

        session.setAttribute("res", res);
        return "redirect:/login/registerfail";
    }

    @RequestMapping(value = "/registersuccess", method = {RequestMethod.GET, RequestMethod.POST})
    public String registerSuccess() {
        return "/registersuccess";
    }

    @RequestMapping(value = "/registerfail", method = {RequestMethod.GET, RequestMethod.POST})
    public String registerFail() {
        return "/registerfail";
    }
}
