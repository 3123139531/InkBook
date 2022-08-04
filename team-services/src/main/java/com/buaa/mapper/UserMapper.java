package com.buaa.mapper;

import com.buaa.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import static com.buaa.pojo.User.*;

@Mapper
public interface UserMapper {
    public static final String userTable = "user";
    @Insert("insert into user("+USER_ID+","+USER_NICKNAME+","+USER_NAME+","+EMAIL+","+PASSWORD+","+PROFILE_PIC_URL+") " +
            "values(#{uNickname},#{uName},#{email},#{password},#{profilePic})")
    public void insertUser(User user);

    @Select("select * from user where "+USER_NAME+" = #{user_name}")
    public User selectUserByName(String user_name);

    @Update("update user set "+USER_NICKNAME+" = #{uNickname}, "+PROFILE_PIC_URL+" = #{profilePicUrl}, "+USER_NAME+" = #{uName}, "+EMAIL+" = #{email} , "+PASSWORD+" = #{password}" +
            "where u_name = #{uName}")
    public User updateUserInfo(String nameOfUserToUpdate,User newUserInfo);

    @Update("update user set u_nickname = #{uNickname} where u_name = #{uName}")
    public User updateUserNickname(User user);

    @Update("update user set profile_pic = #{profilePicUrl} where u_name = #{uName}")
    public User updateUserProfilePic(User user);
}