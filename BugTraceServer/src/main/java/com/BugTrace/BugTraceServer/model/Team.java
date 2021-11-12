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
        Card testCard = new Card(UUID.randomUUID(),"Test","Klaidas Serelis","11/4/21",Impact.HIGH);
        List<String> keywords = new LinkedList<>();
        keywords.add("zoom");
        keywords.add("Cake");
        keywords.add("Wroom");
        keywords.add("Caboom");

        testCard.setKeywords(keywords);
        toDos.add(testCard);



    }

    public int addMember()
    {
        return 0;
    }

    public int removeMember()
    {
        return 0;
    }

    public int addTodo(Card card)
    {
        this.toDos.add(card);
        return 1;
    }

    public int removeTodo(UUID cardId)
    {
        if(toDos.stream().filter(cardToFind -> cardToFind.getCardId().equals(cardId)).findAny().orElse(null)!=null)
        {
            toDos.removeIf(cardToRemove -> cardToRemove.getCardId().equals(cardId));
            return 1;
        }
        return 0;
    }

    public int assignTodo(Card card)
    {
        Card cardToMove =toDos.stream().filter(cardToFind -> cardToFind.getCardId().equals(card.getCardId())).findAny().orElse(null);
        if(cardToMove!=null)
        {
            this.inProgress.add(card);
            toDos.removeIf(cardToRemove -> cardToRemove.getCardId().equals(card.getCardId()));
            return 1;
        }
        return 0;
    }
    public int addInProgress(Card card)
    {
        this.inProgress.add(card);
        return 1;
    }

    public int completeTodo()
    {
        return 0;
    }

    public int completeInProgress(Card card)
    {
        Card cardToMove =this.inProgress.stream().filter(cardToFind -> cardToFind.getCardId().equals(card.getCardId())).findAny().orElse(null);
        if(cardToMove!=null)
        {
            this.completed.add(card);
            inProgress.removeIf(cardToRemove -> cardToRemove.getCardId().equals(card.getCardId()));
            return 1;
        }
        return 0;
    }

    public int removeInProgress(UUID cardId)
    {
        if(inProgress.stream().filter(cardToFind -> cardToFind.getCardId().equals(cardId)).findAny().orElse(null)!=null)
        {
            inProgress.removeIf(cardToRemove -> cardToRemove.getCardId().equals(cardId));
            return 1;
        }
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
