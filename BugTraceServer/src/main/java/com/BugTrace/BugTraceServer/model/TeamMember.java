package com.BugTrace.BugTraceServer.model;

public class TeamMember
{
    private User user;
    private Level level;

    public TeamMember(User user, Level level) {
        this.user = user;
        this.level = level;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }


}
