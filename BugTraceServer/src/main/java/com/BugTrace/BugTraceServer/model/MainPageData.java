package com.BugTrace.BugTraceServer.model;

public class MainPageData
{
    private User user;
    private Team team;

    public MainPageData(User user, Team team) {
        this.user = user;
        this.team = team;
    }

    @Override
    public String toString() {
        return "MainPageData{" +
                "user=" + user.toString() +
                ", team=" + team.toString() +
                '}';
    }

    public User getUser() {
        return user;
    }

    public Team getTeam() {
        return team;
    }
}
