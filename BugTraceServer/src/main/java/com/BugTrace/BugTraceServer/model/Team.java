package com.BugTrace.BugTraceServer.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Team
{
    private String name;
    private UUID teamId;
    private List<TeamMember> teamMembers = new LinkedList<>();
    private List<Card> toDos = new LinkedList<>();
    private List<Card> inProgress = new LinkedList<>();
    private List<Card> completed = new LinkedList<>();

    public Team(String name, User user)
    {
        this.name = name;
        teamId = UUID.randomUUID();
        teamMembers.add(new TeamMember(user,Level.LEADER));
        user.setTeamId(teamId);
        toDos.add(new Card(UUID.randomUUID(),"Test","Klaidas Serelis","11/4/21",Impact.HIGH));
    }

    public int addMember()
    {
        return 0;
    }

    public int removeMember()
    {
        return 0;
    }

    public int addTodo()
    {
        return 0;
    }

    public int assignTodo()
    {
        return 0;
    }

    public int completeTodo()
    {
        return 0;
    }

    public int completeInProgress()
    {
        return 0;
    }

    public String getName() {
        return name;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public List<Card> getToDos() {
        return toDos;
    }

    public List<Card> getInProgress() {
        return inProgress;
    }

    public List<Card> getCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", teamId=" + teamId +
                ", teamMembers=" + teamMembers +
                ", toDos=" + toDos +
                ", inProgress=" + inProgress +
                ", completed=" + completed +
                '}';
    }
}
