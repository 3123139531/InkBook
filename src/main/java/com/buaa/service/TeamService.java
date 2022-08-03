package com.buaa.service;

import com.buaa.pojo.Team;
import com.buaa.pojo.User;

public interface TeamService {
    public Team selectTeamById(int id);

    public Team[] createTeam(Team team, User creator);

    //TODO: invite/ add member
    public void addTeamMember(Team team, User user);

    //TODO: remove/ delete member
    public void removeTeamMember(Team team,User user);

    //TODO: select teams user is in
    public Team[] getUserTeams(User user);
    //TODO: change/ update member position
    //TODO: select team members
    public User[] getTeamMembers(Team team);
}