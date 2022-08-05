package com.buaa.pojo;

import lombok.Data;

@Data
public class Team {
    public static final String TEAM_ID = "t_id";
    public static final String TEAM_NAME = "t_name";
    public static final String TEAM_BRIEF = "t_brief";

    private int tid;
    private String tname;
    private String tbrief;
}
