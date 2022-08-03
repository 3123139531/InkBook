package com.buaa.controller;

import com.buaa.controller.utils.R;
import com.buaa.pojo.User;
import com.buaa.service.UserService;
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
    public R userInfo(HttpSession session) throws Exception{
        String username = session.getAttribute("username").toString();
        if(username == null || username.equals("")){
            throw new Exception("login first");
        }
        else{
            R r = new R();
            r.setData(userService.findUserByName(username));
            r.setFlag(true);
            return r;
        }
    }

    @ApiOperation(value = "注册")
    @PostMapping("/user/register")
    public R userRegister(@RequestBody(required=false) User user) {
        User tryRegisterUser = userService.findUserByName(user.getUName());
        if(tryRegisterUser != null){
//            throw new Exception("User already exists");
            return new R();
        }
        else {
//            User user = new User();
//            user.setuName(user_name);
//            user.setEmail(email);
//            user.setPassword(password);

            R r = new R();
            r.setData(userService.insertRegisteredUser(user));
            r.setFlag(true);
            return r;
        }
    }

    @ApiOperation(value = "登录")
    @PostMapping("/user/login")
    public R userLogin(@RequestBody User user, HttpSession session) throws Exception{
//        User user = new User();
//        user.setuName(user_name);
//        user.setPassword(password);
        //find user by name
        User tryLoginUser = userService.findUserByName(user.getUName());
        if(tryLoginUser != null) {
            //if found check password
            if(tryLoginUser.getPassword().equals(user.getPassword())){
                //login success
                session.setAttribute("username",user.getUName());
                R r = new R();
                r.setData(userService.insertRegisteredUser(tryLoginUser));
                r.setFlag(true);
                return r;
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