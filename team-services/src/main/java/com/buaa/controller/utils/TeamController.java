package com.buaa.controller.utils;

import com.buaa.pojo.Team;
import com.buaa.pojo.User;
import com.buaa.service.TeamService;
import com.buaa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Api(tags = "团队管理")
@RestController
public class TeamController {
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;

    @ApiOperation(value = "根据团队id获取团队成员列表")
    @GetMapping("/team/{team_id}/members")
    public R showTeamMembers(@PathVariable("team_id") int team_id){
        Team team = new Team();
        team.setTid(team_id);
        //return
        R r = new R();
        r.setData(teamService.getTeamMembers(team));
        r.setFlag(true);
        return r;
    }

    @ApiOperation(value = "获取用户所在的全部团队列表")
    @GetMapping("/team")
    public R showUserTeam(HttpSession session){
        String username = session.getAttribute("username").toString();
        User user = userService.findUserByName(username);
        //return
        R r = new R();
        r.setData(teamService.getUserTeams(user));
        r.setFlag(true);
        return r;
    }

    @ApiOperation(value = "创建团队")
    @PostMapping("/team")
    public R insertTeam(@RequestParam("team_name") String team_name, @RequestParam("team_name") String team_description, HttpSession session){
        Team team = new Team();
        team.setTname(team_name);
        team.setTbrief(team_description);

        String username = session.getAttribute("username").toString();
        User creator = userService.findUserByName(username);

        //return
        R r = new R();
        r.setData(teamService.createTeam(team,creator));
        r.setFlag(true);
        return r;
    }

    @ApiOperation(value = "邀请用户加入团队")
    @PostMapping("/team/{team_id}")
    public void inviteUserToTeam(@PathVariable("team_id") int t_id,@RequestParam("username") String username){
        Team team = teamService.selectTeamById(t_id);
        User user = userService.findUserByName(username);
        teamService.addTeamMember(team,user);
    }

    @ApiOperation(value = "团队移除用户")
    @DeleteMapping("/team/{team_id}")
    public void removeUserFromTeam(@PathVariable("team_id") int t_id,@RequestParam("username") String username){
        Team team = teamService.selectTeamById(t_id);
        User user = userService.findUserByName(username);
        teamService.removeTeamMember(team,user);
    }
}
