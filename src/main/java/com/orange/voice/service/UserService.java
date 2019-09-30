package com.orange.voice.service;

import com.orange.voice.bean.User;
import com.orange.voice.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @SuppressWarnings("all")
    @Autowired
    UserMapper userMapper;

    public List<User> getList(){
        return userMapper.getList();
    }
}
