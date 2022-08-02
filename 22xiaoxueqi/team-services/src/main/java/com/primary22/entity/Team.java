package com.primary22.entity;

import org.springframework.stereotype.Repository;

@Repository
public class Team {
    private int tId;
    private String tName;
    private String brief;

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
