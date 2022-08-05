package com.buaa.mapper;

import com.buaa.pojo.Team;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TeamMapper {

    @Select("select * from team")
    public Team[] selectAllTeam();

    @Select("select * from team where t_id = #{tid}")
    public Team selectTeamByID(int id);

    @Insert("INSERT INTO team(t_name) values(#{tname})")
    public void insertTeam(Team team);

    @Delete("delete")
    public void deleteTeam();

}