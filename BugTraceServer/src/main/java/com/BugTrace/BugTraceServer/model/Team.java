package com.BugTrace.BugTraceServer.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity(name = "team")
public class Team
{
    private String name;
    @Id
    private UUID teamId;
    @OneToMany(cascade= CascadeType.PERSIST)
    private List<TeamMember> teamMembers = new LinkedList<>();
    @OneToMany(cascade=CascadeType.PERSIST)
    private List<Card> cards = new LinkedList<>();

    public Team(String name, User user)
    {
        this.name = name;
        this.teamId = UUID.randomUUID(); // This makes no sense
        //teamMembers.add(new TeamMember(user,Level.LEADER));
        //user.setTeamId(teamId);
    }

    public Team() {

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
        card.setTypeOfCard(TypeOfCard.TODO);
        cards.add(card);
        return 1;
    }

    public int removeTodo(UUID cardId)
    {
        if(cards.stream().filter(cardToFind -> cardToFind.getCardId().equals(cardId)).findAny().orElse(null)!=null)
        {
            cards.removeIf(cardToRemove -> cardToRemove.getCardId().equals(cardId));
            return 1;
        }
        return 0;
    }

    public int assignTodo(Card card)
    {
        Card cardToMove =cards.stream().filter(cardToFind -> cardToFind.getCardId().equals(card.getCardId())).findAny().orElse(null);
        if(cardToMove!=null)
        {
            card.setTypeOfCard(TypeOfCard.INPROGRESS);
            cards.removeIf(cardToRemove -> cardToRemove.getCardId().equals(card.getCardId()));
            cards.add(cardToMove);
            return 1;
        }
        return 0;
    }
    public int addInProgress(Card card)
    {
        card.setTypeOfCard(TypeOfCard.INPROGRESS);
        this.cards.add(card);
        return 1;
    }

    public int completeTodo()
    {
        return 0;
    }

    public int completeInProgress(Card card)
    {
        Card cardToMove =this.cards.stream().filter(cardToFind -> cardToFind.getCardId().equals(card.getCardId())).findAny().orElse(null);
        if(cardToMove!=null)
        {
            this.cards.add(card);
            cards.removeIf(cardToRemove -> cardToRemove.getCardId().equals(card.getCardId()));
            return 1;
        }
        return 0;
    }

    public int removeInProgress(UUID cardId)
    {
        if(cards.stream().filter(cardToFind -> cardToFind.getCardId().equals(cardId)).findAny().orElse(null)!=null)
        {
            cards.removeIf(cardToRemove -> cardToRemove.getCardId().equals(cardId));
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
        return cards;
    }

    public List<Card> getInProgress() {
        return cards;
    }

    public List<Card> getCompleted() {
        return cards;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", teamId=" + teamId +
                ", teamMembers=" + teamMembers +
                ", cards=" + cards +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
