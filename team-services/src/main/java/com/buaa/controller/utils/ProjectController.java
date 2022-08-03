package com.buaa.controller.utils;

import com.buaa.controller.utils.R;
import com.buaa.pojo.Project;
import com.buaa.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public R createProject(@RequestBody Project project) {
        return new R(true, projectService.createProject(project.getTid(), project.getPname()));
    }

    @DeleteMapping("/{id}")
    public R deleteProject(@PathVariable int id) {
        return new R(projectService.deleteProjectById(id));
    }

    @PutMapping
    public R renameProject(@RequestBody Project project) {
        return new R(projectService.renameProject(project.getPid(), project.getPname()));
    }

    @GetMapping("/team/{id}")
    public R selectByTeam(@PathVariable int id) {
        R r = new R();
        r.setFlag(true);
        r.setData(projectService.selectByTeam(id));
        return r;
    }

    @GetMapping("/{id}")
    public R selectById(@PathVariable int id) {
        R r = new R();
        r.setFlag(true);
        r.setData(projectService.selectById(id));
        return r;
    }
}
