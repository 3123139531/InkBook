package com.buaa.impl;

import com.buaa.pojo.Team;
import com.buaa.pojo.TeamMember;
import com.buaa.pojo.User;
import com.buaa.mapper.TeamMapper;
import com.buaa.mapper.TeamMemberMapper;
import com.buaa.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    TeamMemberMapper teamMemberMapper;

    public Team selectTeamById(int id){
        return teamMapper.selectTeamByID(id);
    }

    public Team[] createTeam(Team team, User creator){
        System.out.println(team.toString());
        System.out.println(team.toString());
        System.out.println(team.toString());
        teamMapper.insertTeam(team);
        int tid = team.getTid();
        int uid = creator.getUId();
        TeamMember teamCreator = new TeamMember();
        teamCreator.setTId(tid);
        teamCreator.setUId(uid);
        teamCreator.setUPosition(3);
        teamMemberMapper.insertTeamMember(teamCreator);
        return teamMapper.selectAllTeam();
    }

    //TODO: invite/ add member
    public void addTeamMember(Team team, User user){
        int tid = team.getTid();
        int uid = user.getUId();
        TeamMember newTeamMember = new TeamMember();
        newTeamMember.setTId(tid);
        newTeamMember.setUId(uid);
        newTeamMember.setUPosition(1);
        teamMemberMapper.insertTeamMember(newTeamMember);
    }

    //TODO: remove/ delete member
    public void removeTeamMember(Team team,User user){
        int tid = team.getTid();
        int uid = user.getUId();
        TeamMember memberToDelete = new TeamMember();
        memberToDelete.setTId(tid);
        memberToDelete.setUId(uid);
        teamMemberMapper.deleteTeamMember(memberToDelete);
    }

    //TODO: select teams user is in
    public Team[] getUserTeams(User user){
        return teamMemberMapper.selectTeamsByUserId(user);
    }
    //TODO: change/ update member position
    public int selectMemberPosition(Team team,User user){
        int tid = team.getTid();
        int uid = user.getUId();
        TeamMember memberToFind = new TeamMember();
        memberToFind.setTId(tid);
        memberToFind.setUId(uid);
        return teamMemberMapper.selectMemberPositionInTeam(memberToFind);
    }
    public void updateMemberPosition(Team team,User user, int position){
        int tid = team.getTid();
        int uid = user.getUId();
        TeamMember memberToChange = new TeamMember();
        memberToChange.setTId(tid);
        memberToChange.setUId(uid);
        memberToChange.setUPosition(position);
        teamMemberMapper.updateMemberPositionInTeam(memberToChange);
    }
    //TODO: select team members
    public User[] getTeamMembers(Team team){
        return teamMemberMapper.selectMembersByTeam(team);
    }
}