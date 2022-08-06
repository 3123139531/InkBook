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
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户管理")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "用户信息")
    @GetMapping("/user/info")
    public R userInfo(HttpSession session) throws Exception{
        String username = session.getAttribute("username").toString();
        if(username == null || username.equals("")){
            R r = new R();
            r.setData(userService.findUserByName(username));
            r.setFlag(true);
            return r;
        }
        else{
            R r = new R();
            r.setData(userService.findUserByName(username));
            r.setFlag(true);
            return r;
        }
    }

    @ApiOperation(value = "根据用户名查询用户信息")
    @GetMapping("/user/info/{u_name}")
    public R userInfoByName(@PathVariable("u_name") String username,HttpSession session) throws Exception{
        R r = new R();
        r.setData(userService.findUserByName(username));
        r.setFlag(true);
        return r;
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/user/info")
    public R updateUserInfo(HttpSession session, @RequestBody User newUserInfo) throws Exception{
        String username = (String) session.getAttribute("username");
        userService.updateUserInfo(username,newUserInfo);

        R r = new R();
        r.setData(newUserInfo);
        r.setFlag(true);
        return r;

    }

    @ApiOperation(value = "注册")
    @PostMapping("/user/register")
    public R userRegister(@RequestBody(required=false) User user) {
        User tryRegisterUser = userService.findUserByName(user.getUName());
        if(tryRegisterUser != null){
//            throw new Exception("User already exists");
            R r = new R();
            r.setData("this username is taken");
            r.setFlag(false);
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
        R r = new R();
        if(tryLoginUser != null) {
            //if found check password
            if(tryLoginUser.getPassword().equals(user.getPassword())){
                //login success
                session.setAttribute("username",user.getUName());

                r.setData(userService.findUserByName(tryLoginUser.getUName()));
                r.setFlag(true);
                return r;
            }
            else{
                //output incorrect password
                r.setData("incorrect password");
                r.setFlag(false);
                return r;
            }
        }
        else{
            //if not output: user doesn't exist
            r.setData("user doesn't exist");
            r.setFlag(false);
            return r;
        }
    }

    @PostMapping("/sendCode")
    public R sendCode(@RequestBody User user){
//        System.out.println(user);
        boolean flag = userService.sendCode(user);
        if (flag)
            return new R(true, "邮件发送成功，请前往您的邮箱进行注册验证");
        else
            return new R(false, "邮件发送失败");
    }

    // 判断是否注册成功
    @GetMapping("/lookCode/{token}")
    public R lookCode(@PathVariable("token")String token){
        boolean flag = userService.eqToken(token);
        if (flag)
            return new R(true, "注册成功");
        else
            return new R(false, "注册码过期，请重新注册");
    }

}