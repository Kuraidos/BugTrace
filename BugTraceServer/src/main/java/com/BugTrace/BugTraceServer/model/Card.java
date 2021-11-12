package com.BugTrace.BugTraceServer.model;

import java.util.List;
import java.util.UUID;

public class Card
{
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
    private List<String> keywords;

    public Card(UUID cardId, String title, String creator, String dateCreated, Impact impact) {
        this.cardId = cardId;
        this.title = title;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.impact = impact;
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
}
