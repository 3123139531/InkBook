package com.buaa.pojo;

import lombok.Data;
import org.springframework.stereotype.Repository;

//@Data
@Repository
@Data
public class User {
    public static final String USER_ID = "u_id";
    public static final String USER_NICKNAME = "u_Nickname";
    public static final String USER_NAME = "u_name";
    public static final String EMAIL= "email";
    public static final String PASSWORD = "password";
    public static final String PROFILE_PIC_URL= "profile_pic_url";

    private int uId;
    private String uNickname;
    private String uName;
    private String email;
    private String password;
    private String profilePic;
}