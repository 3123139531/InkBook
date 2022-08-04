package com.buaa.pojo;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class User {

    private int uId;
    private String uNickname;
    private String uName;
    private String email;
    private String password;
    private String profilePicUrl;
}