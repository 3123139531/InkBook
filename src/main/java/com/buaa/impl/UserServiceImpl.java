package com.buaa.impl;

import com.buaa.pojo.User;
import com.buaa.mapper.UserMapper;
import com.buaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public User insertRegisteredUser(User user){
        userMapper.insertUser(user);
        return user;
    }

    public User findUserByName(String username){
        return userMapper.selectUserByName(username);
    }

    public void updateUserInfo(String nameOfUserToUpdate, User newUserInfo) {
        userMapper.updateUserInfo(nameOfUserToUpdate,newUserInfo);
    }
}