package com.BugTrace.BugTraceServer.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name="team_member")
public class TeamMember
{
    @Id
    private UUID memberId;
    private String email;
    private String name;
    private Level level;

    public TeamMember(User user, Level level) {

        this.email=user.getEmail();
        this.name=user.getUsername();
        this.level = level;
        this.memberId=UUID.randomUUID();
    }

    public TeamMember() {

    }

    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }


}
