package com.primary22.service;

import com.primary22.entity.Team;
import com.primary22.entity.User;
import com.primary22.mapper.TeamMapper;
import com.primary22.mapper.TeamMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    TeamMemberMapper teamMemberMapper;

    public Team selectTeamById(int id){
        return teamMapper.selectTeamByID(id);
    }

    public Team[] createTeam(Team team, User creator){
        teamMapper.insertTeam(team);
        //TODO add self as team creator,position=3
        teamMemberMapper.insertTeamMember(team,creator,3);
        return teamMapper.selectAllTeam();
    }

    //TODO: invite/ add member
    public void addTeamMember(Team team, User user){
        teamMemberMapper.insertTeamMember(team,user,1);
    }

    //TODO: remove/ delete member
    public void removeTeamMember(Team team,User user){
        teamMemberMapper.deleteTeamMember(team,user);
    }

    //TODO: select teams user is in
    public Team[] getUserTeams(User user){
        return teamMemberMapper.selectTeamsByUserId(user);
    }
    //TODO: change/ update member position
    //TODO: select team members
    public User[] getTeamMembers(Team team){
        return teamMemberMapper.selectMembersByTeam(team);
    }
}
