package com.primary22.controller;

import com.primary22.entity.User;
import com.primary22.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Api(tags = "用户管理")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "用户信息")
    @GetMapping("/user/info")
    public User userInfo(HttpSession session) throws Exception{
        String username = session.getAttribute("username").toString();
        if(username == null || username.equals("")){
            throw new Exception("login first");
        }
        else{
            return userService.findUserByName(username);
        }
    }

    @ApiOperation(value = "注册")
    @PostMapping("/user/register")
    public User userRegister(@RequestBody String user_name, @RequestBody String email, @RequestBody String password, HttpSession session) throws Exception{
        User tryRegisterUser = userService.findUserByName(user_name);
        if(tryRegisterUser != null){
            throw new Exception("User already exists");
        }
        else {
            User user = new User();
            user.setuName(user_name);
            user.setEmail(email);
            user.setPassword(password);
            return userService.insertRegisteredUser(user);
        }
    }

    @ApiOperation(value = "登录")
    @PostMapping("/user/login")
    public User userLogin(@RequestBody String user_name, @RequestBody String password, HttpSession session) throws Exception{
//        User user = new User();
//        user.setuName(user_name);
//        user.setPassword(password);
        //find user by name
        User tryLoginUser = userService.findUserByName(user_name);
        if(tryLoginUser != null) {
            //if found check password
            if(tryLoginUser.getPassword().equals(password)){
                //login success
                session.setAttribute("username",user_name);
                return userService.insertRegisteredUser(tryLoginUser);
            }
            else{
                //output incorrect password
                throw new Exception("Incorrect Password");
            }
        }
        else{
            //if not output: user doesn't exist
            throw new Exception("User doesn't exist");
        }
    }

}
