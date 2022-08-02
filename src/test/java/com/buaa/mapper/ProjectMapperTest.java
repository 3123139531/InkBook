package com.buaa.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.buaa.pojo.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.PrimitiveIterator;

@SpringBootTest
public class ProjectMapperTest {

    @Autowired
    ProjectMapper projectMapper;

    @Test
    public void testSave() {
        Project project = new Project();
        project.setTid(3);
        project.setStatus("doing");
        project.setPname("北京大学食堂新建");

        projectMapper.insertProject(project);
    }

    @Test
    public void testDelete() {
        QueryWrapper<Project> qw = new QueryWrapper<>();
        qw.eq("pid", 2);

        projectMapper.delete(qw);
    }

    @Test
    public void testUpdate() {
        Project project = new Project();
        project.setTid(3);
        project.setStatus("finish");
        project.setPname("北京大学食堂新建工程");

        QueryWrapper<Project> qw = new QueryWrapper<>();
        // 第一个是数据库中列名，第二个是eq的具体值
        qw.eq("pid", 2);

        projectMapper.update(project, qw);
    }

    @Test
    public void testGetById() {
        System.out.println(projectMapper.getProjectById(1));
    }

    @Test
    public void testGetByTeam() {
        System.out.println(projectMapper.getProjectByTeam(1));
    }

    @Test
    public void testChangeName() {
        Project project = new Project();
        project.setPid(3);
        project.setPname("北航合一楼扩建计划");
        projectMapper.updateProjectName(project);
    }

    @Test
    public void testGetByName() {
        projectMapper.getProjectByPName("北航绿园扩建计划");
    }
}
