package com.buaa.controller;

import com.buaa.controller.utils.R;
import com.buaa.pojo.Team;
import com.buaa.pojo.User;
import com.buaa.service.TeamService;
import com.buaa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "团队管理")
@RestController
@CrossOrigin
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
        User[] users = teamService.getTeamMembers(team);
        ArrayList<Map<String,String>> usersList = new ArrayList<>();
        HashMap<String,String> userInfos = new HashMap<String,String>();

        for (User user: users) {
            userInfos.put("uname",user.getUName());
            userInfos.put("uid",String.valueOf(user.getUId()));
            userInfos.put("unickname",user.getUNickname());
            userInfos.put("email",user.getEmail());
            userInfos.put("identity",String.valueOf(teamService.selectMemberPosition(team,user)));
            usersList.add(userInfos);
        }
        R r = new R();
        r.setData(usersList);
        r.setFlag(true);
        return r;
    }

//    @ApiOperation(value = "获取用户所在的全部团队列表")
//    @GetMapping("/team")
//    public R showUserTeam(HttpSession session){
//        String username = session.getAttribute("username").toString();
//        User user = userService.findUserByName(username);
//        //return
//        R r = new R();
//        r.setData(teamService.getUserTeams(user));
//        r.setFlag(true);
//        return r;
//    }

    @ApiOperation(value = "获取用户所在的全部团队列表")
    @GetMapping("/team/{user_name}")
    public R showUserTeam(@PathVariable("user_name") String username){
        User user = userService.findUserByName(username);
        //return
        R r = new R();
        r.setData(teamService.getUserTeams(user));
        r.setFlag(true);
        return r;
    }

//    @ApiOperation(value = "创建团队")
//    @PostMapping("/team")
//    public R insertTeam(@RequestParam("team_name") String team_name, @RequestParam("team_brief") String team_description, HttpSession session){
//        Team team = new Team();
//        team.setTname(team_name);
//        team.setTbrief(team_description);
//
//        String username = session.getAttribute("username").toString();
//        User creator = userService.findUserByName(username);
//
//        //return
//        R r = new R();
//        r.setData(teamService.createTeam(team,creator));
//        r.setFlag(true);
//        return r;
//    }


    @ApiOperation(value = "创建团队")
    @PostMapping("/team/{user_name}")
    public R createTeam(@PathVariable("user_name") String username, @RequestBody Team team){

        User creator = userService.findUserByName(username);

        //return
        R r = new R();
        r.setData(teamService.createTeam(team,creator));
        r.setFlag(true);
        return r;
    }

//    @ApiOperation(value = "邀请用户加入团队")
//    @PostMapping("/team/{team_id}")
//    public void inviteUserToTeam(@PathVariable("team_id") int t_id,@RequestParam("username") String username){
//        Team team = teamService.selectTeamById(t_id);
//        User user = userService.findUserByName(username);
//        teamService.addTeamMember(team,user);
//    }

    @ApiOperation(value = "邀请用户加入团队")
    @PostMapping("/team/{team_id}/")
    public void inviteUserToTeam(@PathVariable("team_id") int t_id,@RequestBody User user){
        Team team = teamService.selectTeamById(t_id);
        teamService.addTeamMember(team,user);
    }

//    @ApiOperation(value = "团队移除用户")
//    @DeleteMapping("/team/{team_id}")
//    public void removeUserFromTeam(@PathVariable("team_id") int t_id,@RequestParam("username") String username){
//        Team team = teamService.selectTeamById(t_id);
//        User user = userService.findUserByName(username);
//        teamService.removeTeamMember(team,user);
//    }

//    @ApiOperation(value = "团队移除用户")
//    @DeleteMapping("/team/{team_id}")
//    public void removeUserFromTeam(@PathVariable("team_id") int t_id,@RequestBody User user){
//        Team team = teamService.selectTeamById(t_id);
//        teamService.removeTeamMember(team,user);
//    }

    @ApiOperation(value = "团队移除用户")
    @DeleteMapping("/team/{team_id}/members/{kicker_username}")
    public void removeUserFromTeam(@PathVariable("team_id") int t_id,@PathVariable("kicker_username") String kickerUsername,@RequestBody User userToKick){
        Team team = teamService.selectTeamById(t_id);
        User kicker = userService.findUserByName(kickerUsername);
        int kickerPosition = teamService.selectMemberPosition(team,kicker);
        int userToKickPosition = teamService.selectMemberPosition(team,userToKick);
        if(kickerPosition >=2 && kickerPosition>userToKickPosition) {
            teamService.removeTeamMember(team, userToKick);
        }
    }

    @ApiOperation(value = "修改团队成员权限")
    @PutMapping("/team/{team_id}/members/{changer_username}")
    public void changeUserPosition(@PathVariable("team_id") int t_id,@PathVariable("changer_username") String changerUsername,@RequestBody User userToChange,@RequestParam("target_position") int targetPosition){
        Team team = teamService.selectTeamById(t_id);
        User changer = userService.findUserByName(changerUsername);
        int changerPosition = teamService.selectMemberPosition(team,changer);
        if(changerPosition>=2 && targetPosition<3) {
            teamService.updateMemberPosition(team, userToChange, targetPosition);
        }
    }

}