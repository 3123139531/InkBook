package com.buaa.mapper;

import com.buaa.pojo.Team;
import org.apache.ibatis.annotations.*;

import static com.buaa.pojo.Team.*;

@Mapper
public interface TeamMapper {

    @Select("select * from team")
    public Team[] selectAllTeam();

    @Select("select * from team where +"+TEAM_ID+" = #{id}")
    public Team selectTeamByID(int id);

    @Insert("INSERT INTO team("+TEAM_NAME+","+TEAM_BRIEF+") values(#{tname},#{tbrief})")
    public void insertTeam(Team team);

    //TODO
    @Delete("delete")
    public void deleteTeam();

}