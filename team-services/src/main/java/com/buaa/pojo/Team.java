package com.buaa.pojo;

import lombok.Data;

@Data
public class Team {
    public static final String TEAM_ID = "tid";
    public static final String TEAM_NAME = "tname";
    public static final String TEAM_BRIEF = "tbrief";

    private int tid;
    private String tname;
    private String tbrief;
}
