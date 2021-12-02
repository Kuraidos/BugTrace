package com.BugTrace.BugTraceServer.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;
@Entity(name="card")
public class Card
{
    @Id
    private UUID cardId;
    private String title;
    private String creator;
    private String dateCreated;
    private String dateAssigned="";
    private String dateCompleted="";
    private String assignedTo="";
    private String completedBy="";
    private String description="";
    private Impact impact;
    @ElementCollection
    private List<String> keywords;
    private TypeOfCard typeOfCard;

    public Card(UUID cardId, String title, String creator, String dateCreated, Impact impact) {
        this.cardId = cardId;
        this.title = title;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.impact = impact;
    }

    public Card() {

    }

    public UUID getCardId() {
        return cardId;
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateAssigned() {
        return dateAssigned;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public String getCompletedBy() {
        return completedBy;
    }

    public String getDescription() {
        return description;
    }

    public Impact getImpact() {
        return impact;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateAssigned(String dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setCompletedBy(String completedBy) {
        this.completedBy = completedBy;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImpact(Impact impact) {
        this.impact = impact;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public TypeOfCard getTypeOfCard() {
        return typeOfCard;
    }

    public void setTypeOfCard(TypeOfCard typeOfCard) {
        this.typeOfCard = typeOfCard;
    }

    public void setCardId(UUID cardId) {
        this.cardId = cardId;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", title='" + title + '\'' +
                ", creator='" + creator + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateAssigned='" + dateAssigned + '\'' +
                ", dateCompleted='" + dateCompleted + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", completedBy='" + completedBy + '\'' +
                ", description='" + description + '\'' +
                ", impact=" + impact +
                ", keywords=" + keywords +
                ", typeOfCard=" + typeOfCard +
                '}';
    }
}
