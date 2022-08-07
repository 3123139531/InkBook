package com.buaa.controller;

import com.buaa.controller.utils.R;
import com.buaa.pojo.Project;
import com.buaa.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 新建项目
     * @param "组号tid, 项目名pName"
     * @return "新项目id"
     */
    @PostMapping
    public R createProject(@RequestBody Project project) {
        int tid = project.getTid();
        String name = project.getPName();
        if(projectService.checkNameRepeat(tid, name))
            return new R(false, "组内已有同名项目，请改名！");
        return new R(true, projectService.createProject(tid,name),
                "新项目创建成功！");
    }

    /**
     * 移入回收站
     */
    @DeleteMapping("/{pid}")
    public R removeProject(@PathVariable int pid) {
        return new R(projectService.updateStatus(pid, "trash"), null,
                "成功移入回收站！");
    }

    /**
     * 从回收站移除
     */
    @DeleteMapping("/!/{pid}")
    public R deleteProject(@PathVariable int pid) {
        return new R(projectService.deleteProjectById(pid), null,
                "从回收站移除成功！");
    }

    /**
     * 项目更名
     * @param “pid, pName”
     */
    @PutMapping
    public R renameProject(@RequestBody Project project) {
        return new R(projectService.renameProject(project.getPid(), project.getPName()),
                null, "项目名称已变更");
    }

    /**
     * 根据组号索引项目
     * @param tid
     */
    @GetMapping("/team/{tid}")
    public R selectByTeam(@PathVariable int tid) {
        R r = new R();
        r.setFlag(true);
        r.setData(projectService.selectByTeam(tid));
        r.setMsg("查询成功！");
        return r;
    }

    /**
     * 根据项目号索引项目
     * @param pid
     */
    @GetMapping("/{pid}")
    public R selectById(@PathVariable int pid) {
        R r = new R();
        r.setFlag(true);
        r.setData(projectService.selectById(pid));
        r.setMsg("查询成功！");
        return r;
    }

    @GetMapping("/trash/{tid}")
    public R selectTrash(@PathVariable int tid) {
        return new R(true, projectService.selectProjectByStatus(
                tid, "trash"), "查询成功");
    }

    @GetMapping("/finish/{tid}")
    public R selectFinish(@PathVariable int tid) {
        return new R(true, projectService.selectProjectByStatus(
                tid, "finish"), "查询成功");
    }

    @GetMapping("/doing/{tid}")
    public R selectDoing(@PathVariable int tid) {
        return new R(true, projectService.selectProjectByStatus(
                tid, "doing"), "查询成功");
    }

    @GetMapping("/like/{tid}/{key}")
    public R selectLike(@PathVariable String key, @PathVariable int tid) {
        return new R(true, projectService.selectProjectLike(tid, key),
                "搜索成功");
    }

    @GetMapping("/copy/{pid}")
    public R copyProject(@PathVariable int pid) {
        return new R(true, projectService.copyProject(pid), "项目复制成功");
    }
}
