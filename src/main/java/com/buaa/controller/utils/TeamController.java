package com.primary22.controller;

import com.primary22.entity.Team;
import com.primary22.entity.User;
import com.primary22.service.TeamService;
import com.primary22.service.UserService;
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
    @GetMapping("/team/{team_id}")
    public User[] showTeamMembers(@PathVariable("team_id") int team_id){
        Team team = new Team();
        team.settId(team_id);
        return teamService.getTeamMembers(team);
    }

    @ApiOperation(value = "获取用户所在的全部团队列表")
    @GetMapping("/team")
    public Team[] showUserTeam(HttpSession session){
        String username = session.getAttribute("username").toString();
        User user = userService.findUserByName(username);
        return teamService.getUserTeams(user);
    }

    @ApiOperation(value = "创建团队")
    @PostMapping("/team")
    public Team[] insertTeam(@RequestParam("team_name") String team_name, @RequestParam("team_name") String team_description, HttpSession session){
        Team team = new Team();
        team.settName(team_name);
        team.setBrief(team_description);

        String username = session.getAttribute("username").toString();
        User creator = userService.findUserByName(username);

        return teamService.createTeam(team,creator);
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
