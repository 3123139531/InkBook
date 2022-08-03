package com.buaa.mapper;

import com.buaa.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    public static final String userTable = "user";
    @Insert("insert into user(u_nickname,u_name,email,password,profile_pic) " +
            "values(#{uNickname},#{uName},#{email},#{password},#{profilePic})")
    public void insertUser(User user);

    @Select("select * from user where u_name = #{user_name}")
    public User selectUserByName(String user_name);

    @Update("update user set u_nickname = #{uNickname} where u_name = #{uName}")
    public User updateUserNickname(User user);
}
