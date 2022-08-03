package com.buaa.service;

import com.buaa.pojo.Project;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ProjectService {
    int createProject(int tid, String name);
    boolean deleteProjectById(int id);
    boolean renameProject(int pid, String name);
    Project selectById(int id);
    List<Project> selectByTeam(int id);
    Project selectByName(String name);
}
