package com.orange.voice.service;

import com.orange.voice.bean.User;
import com.orange.voice.constant.UserConstant;
import com.orange.voice.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    @SuppressWarnings("all")
    @Autowired
    UserMapper userMapper;

    public String register(User user) {
        if (!isValidString(user.getUserName())) {
            return UserConstant.USERNAME_INVALID;
        }

        if (!isValidString(user.getPassWord())) {
            return UserConstant.PASSWORD_INVALID;
        }

        if (userMapper.getUserByUserName(user.getUserName()) != null) {
            return UserConstant.USERNAME_EXIST;
        }

        userMapper.setNewUser(user);
        return UserConstant.REGISTER_SUCCESS;
    }

    public String login(String username, String password) {
        LOGGER.info("username", username);
        LOGGER.info("password", password);
        if (!isValidString(username)) {
            return UserConstant.USERNAME_INVALID;
        }

        if (!isValidString(password)) {
            return UserConstant.PASSWORD_INVALID;
        }

        User user = userMapper.getUserByUserNameAndPassword(username, password);
        if (user == null) {
            return UserConstant.USER_PWD_ERROR;
        }

        if (user.isVerify()) {
            //todo
            if (verify()) {
                return UserConstant.LOGIN_SUCCESS;
            }else {
                return UserConstant.LOGIN_FAILED;
            }
        }

        return UserConstant.LOGIN_SUCCESS;
    }

    private boolean verify() {
        Random random = new Random();
        int num = random.nextInt(Integer.MAX_VALUE);
        //todo
        LOGGER.info("verify");
        return true;
    }

    private boolean isValidString(String s) {
        for (char c : s.toCharArray()) {
            if (!isNumOrChar(c)) {
                return false;
            }
        }
        return s.length() != 0;
    }

    private boolean isNumOrChar(char c) {
        if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        }
        return false;
    }
}
