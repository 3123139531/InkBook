package com.buaa.mapper;

import com.buaa.pojo.Team;
import com.buaa.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TeamMemberMapper {

    @Insert("insert into team_member(t_id,u_id,u_position) values(#{uId},#{tid},#{position})")
    public void insertTeamMember(Team team, User user, int position);

    @Delete("delete from team_member where t_id = #{tid} and u_id = #{uId}")
    public void deleteTeamMember(Team team, User user);

    @Select("select * from user where u_id in (select u_id from team_member where t_id = #{tid} )")
    public User[] selectMembersByTeam(Team team);

    @Select("select * from team where t_id in (select t_id from team_member where u_id=#{uId})")
    public Team[] selectTeamsByUserId(User user);

    @Update("update team_member set u_position=#{position} where t_id=#{tid} and u_id=#{uId}")
    public void updateMemberPositionInTeam(Team team,User user, int position);
}
