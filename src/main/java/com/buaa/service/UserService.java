package com.buaa.service;

import com.buaa.mapper.UserMapper;
import com.buaa.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    public User insertRegisteredUser(User user);

    public User findUserByName(String username);

    public void updateUserInfo(String nameOfUserToUpdate,User newUserInfo);
}