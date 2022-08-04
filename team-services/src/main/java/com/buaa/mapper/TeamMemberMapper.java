package com.buaa.mapper;

import com.buaa.pojo.Team;
import com.buaa.pojo.User;
import org.apache.ibatis.annotations.*;

import static com.buaa.pojo.Team.*;
import static com.buaa.pojo.User.*;

@Mapper
public interface TeamMemberMapper {

    @Insert("insert into team_member("+TEAM_ID+","+USER_ID+",u_position) values(#{uId},#{tid},#{position})")
    public void insertTeamMember(Team team, User user, int position);

    @Delete("delete from team_member where "+TEAM_ID+" = #{tid} and "+USER_ID+" = #{uId}")
    public void deleteTeamMember(Team team, User user);

    @Select("select * from user where "+USER_ID+" in (select "+USER_ID+" from team_member where "+TEAM_ID+" = #{tid} )")
    public User[] selectMembersByTeam(Team team);

    @Select("select * from team where "+TEAM_ID+" in (select "+TEAM_ID+" from team_member where "+USER_ID+"=#{uId})")
    public Team[] selectTeamsByUserId(User user);

    @Update("update team_member set u_position=#{position} where "+TEAM_ID+"=#{tid} and +"+USER_ID+"=#{uId}")
    public void updateMemberPositionInTeam(Team team,User user, int position);
}