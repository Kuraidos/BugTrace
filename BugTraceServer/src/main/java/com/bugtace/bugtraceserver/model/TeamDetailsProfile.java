package com.bugtace.bugtraceserver.model;

public class TeamDetailsProfile
{
    private String nameOfTeam;
    private Level level;


    public TeamDetailsProfile() {

    }

    public String getNameOfTeam() {
        return nameOfTeam;
    }

    public void setNameOfTeam(String nameOfTeam) {
        this.nameOfTeam = nameOfTeam;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
