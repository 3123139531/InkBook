package com.buaa.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buaa.mapper.ProjectMapper;
import com.buaa.pojo.Project;
import com.buaa.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public int createProject(int tid, String name) {
        Project project = new Project();
        project.setTid(tid);
        project.setPname(name);
        projectMapper.insertProject(project);
        project = projectMapper.getProjectByPName(name);
        return project.getPid();
    }

    @Override
    public boolean deleteProjectById(int id) {
        QueryWrapper<Project> qw = new QueryWrapper<>();
        qw.eq("pid", id);
        return projectMapper.delete(qw) > 0;
    }

    @Override
    public boolean renameProject(int id, String name) {
        Project project = new Project();
        project.setPid(id);
        project.setPname(name);
        projectMapper.updateProjectName(project);
        return true;
    }

    @Override
    public Project selectById(int id) {
        return projectMapper.getProjectById(id);
    }

    @Override
    public List<Project> selectByTeam(int id) {
        return projectMapper.getProjectByTeam(id);
    }

    @Override
    public Project selectByName(String name) {
        return projectMapper.getProjectByPName(name);
    }
}
