package com.primary22.service;

import com.primary22.entity.User;
import com.primary22.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User insertRegisteredUser(User user){
        userMapper.insertUser(user);
        return user;
    }

    public User findUserByName(String username){
        return userMapper.selectUserByName(username);
    }
}
