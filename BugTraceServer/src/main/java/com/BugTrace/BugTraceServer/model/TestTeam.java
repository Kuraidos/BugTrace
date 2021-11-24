package com.BugTrace.BugTraceServer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity(name="test_team")
public class TestTeam
{

    private String name;
    @Id
    private UUID teamId;
    @OneToMany
    private List<TestItem> testItems = new ArrayList<>();

    public TestTeam(String name, User user)
    {
        this.name = name;
        teamId = UUID.randomUUID();
        user.setTeamId(teamId);
    }

    public TestTeam() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public List<TestItem> getTestItems() {
        return testItems;
    }

    public void setTestItems(List<TestItem> testItems) {
        this.testItems = testItems;
    }

    @Override
    public String toString() {
        return "TestTeam{" +
                "name='" + name + '\'' +
                ", teamId=" + teamId +
                ", testItems=" + testItems +
                '}';
    }
}
