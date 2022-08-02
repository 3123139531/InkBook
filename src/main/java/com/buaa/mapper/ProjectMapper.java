package com.buaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buaa.pojo.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    @Select("select * from project where pid = #{id}")
    Project getProjectById(int id);

    @Select("select * from project where tid = #{id}")
    List<Project> getProjectByTeam(int id);

    @Select("select * from project where pname = #{name}")
    Project getProjectByPName(String name);

    @Update("update project set pname = #{pname} where pid = #{pid}")
    void updateProjectName(Project project);

    @Insert("insert into project (tid, pname) values (#{tid}, #{pname})")
    void insertProject(Project project);
}
