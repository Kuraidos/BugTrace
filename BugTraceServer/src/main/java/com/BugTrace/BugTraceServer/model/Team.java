package com.BugTrace.BugTraceServer.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity(name = "team")
public class Team
{
    @Column(nullable = false)
    private String name;
    @Id
    private UUID teamId;
    @OneToMany
    @Column(nullable = false)
    private List<TeamMember> teamMembers = new LinkedList<>();
    @OneToMany
    @Column(nullable = false)
    private List<Card> cards = new LinkedList<>();

    public Team(String name, User user)
    {
        this.name = name;
        this.teamId = UUID.randomUUID();
        user.setTeamId(this.teamId);
        addMember(user,Level.LEADER);
    }

    public Team() {

    }

    public int addMember(User user, Level level)
    {
        teamMembers.add(new TeamMember(user,level));
        return 1;
    }

    public int removeMember()
    {
        return 0;
    }

    public int addCard(Card card)
    {
        cards.add(card);
        return 1;
    }
    //Remove card from any group with provided cardId
    public int removeCard(UUID cardId)
    {
        Card card =cards.stream().filter(cardToFind -> cardToFind.getCardId().equals(cardId)).findAny().orElse(null);
        if(card!=null)
        {
            cards.removeIf(cardToRemove -> cardToRemove.getCardId().equals(cardId));
            return 1;
        }
        return 0;
    }
    public int addTodo(Card card)
    {
        card.setTypeOfCard(TypeOfCard.TODO);
        cards.add(card);
        return 1;
    }

    //Moves card from T0DO to inprogress
    public int assignTodo(Card card)
    {
        Card cardToMove = cards.stream().filter(cardToFind -> cardToFind.getCardId().equals(card.getCardId())).findAny().orElse(null);
        if(cardToMove!=null && card.getTypeOfCard()==TypeOfCard.TODO)
        {
            cardToMove.setTypeOfCard(TypeOfCard.INPROGRESS);
            cards.removeIf(cardToRemove -> cardToRemove.getCardId().equals(cardToMove.getCardId()));
            cards.add(cardToMove);
        }
        else
        {
            cards.add(card);
        }
        return 1;
    }
    //Add card with inprogress tag
    public int addInProgress(Card card)
    {
        card.setTypeOfCard(TypeOfCard.INPROGRESS);
        this.cards.add(card);
        return 1;
    }

    //Moves card from T0DO to completed
    public int completeTodo(UUID cardId)
    {
        Card cardToComplete = cards.stream().filter(cardToFind -> cardToFind.getCardId().equals(cardId)).findAny().orElse(null);
        if(cardToComplete!=null && cardToComplete.getTypeOfCard().equals(TypeOfCard.TODO))
        {
            cards.removeIf(cardToRemove -> cardToRemove.getCardId().equals(cardId));
            cardToComplete.setCompletedBy(cardToComplete.getAssignedTo());
            cardToComplete.setDateCompleted(java.time.LocalDate.now().toString());
            cardToComplete.setTypeOfCard(TypeOfCard.COMPLETED);
            cards.add(cardToComplete);
            return 1;
        }
        return 0;
    }

    //Move card from inpgoress to complted
    public int completeInProgress(Card card)
    {
        Card cardToMove = this.cards.stream().filter(cardToFind -> cardToFind.getCardId().equals(card.getCardId())).findAny().orElse(null);
        if(cardToMove!=null && cardToMove.getTypeOfCard().equals(TypeOfCard.INPROGRESS))
        {
            cardToMove.setTypeOfCard(TypeOfCard.COMPLETED);
            cards.removeIf(cardToRemove -> cardToRemove.getCardId().equals(card.getCardId()));
            this.cards.add(cardToMove);
            return 1;
        }
        return 0;
    }

    //Removes card which is in inprogress
    public int removeInProgress(UUID cardId)
    {
        Card cardToRemove =this.cards.stream().filter(cardToFind -> cardToFind.getCardId().equals(cardId)).findAny().orElse(null);
        if(cardToRemove!=null && cardToRemove.getTypeOfCard().equals(TypeOfCard.INPROGRESS))
        {
            cards.removeIf(cardToDelete -> cardToDelete.getCardId().equals(cardId));
            return 1;
        }
        return 0;
    }


    //Setters and getters
    public String getName() {
        return name;
    }

    public UUID getTeamId() {
        return teamId;
    }


    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public List<Card> getToDos()

    {
        return cards.stream().filter(card -> card.getTypeOfCard().equals(TypeOfCard.TODO)).collect(Collectors.toList());
    }

    public List<Card> getInProgress()
    {
        return cards.stream().filter(card -> card.getTypeOfCard().equals(TypeOfCard.INPROGRESS)).collect(Collectors.toList());
    }

    public List<Card> getCompleted()
    {
        return cards.stream().filter(card -> card.getTypeOfCard().equals(TypeOfCard.COMPLETED)).collect(Collectors.toList());
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

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
