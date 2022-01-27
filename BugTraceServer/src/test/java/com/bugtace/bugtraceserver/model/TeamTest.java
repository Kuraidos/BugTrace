package com.bugtace.bugtraceserver.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    //info about user
    private String username="Kuraido";
    private String email="serelisltu@gmail.com";
    private String password="123123";
    private User testUser;
    private Team underTest;

    //info about card
    private UUID cardId= UUID.randomUUID();
    private String title="testCard";
    private String creator ="Kuraido";
    private String assignTo="Kuraido";
    private Impact priority =Impact.HIGH;
    private List<String> keywords= new ArrayList<>();
    private String description="";
    private Card testCard;

    @BeforeEach
    void setUp()
    {
        testUser=new User(username,email,password);
        underTest= new Team("Test Team",testUser);
        testCard= new Card(cardId,title,creator,java.time.LocalDate.now().toString(),priority);

    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void addMember()
    {
        assertEquals(1,underTest.addMember(testUser,Level.MANAGER));
    }

    @Test
    void removeMember()
    {
        assertEquals(0,underTest.removeMember());
    }

    @Test
    void addCard()
    {
        assertEquals(1,underTest.addCard(new Card()));
    }

    @Test
    void removeCard()
    {
        boolean result = false;
        testCard.setTypeOfCard(TypeOfCard.INPROGRESS);
        underTest.addCard(testCard);

        if(underTest.getInProgress().size()>0)
        {
            underTest.removeCard(testCard.getCardId());
            if(underTest.getInProgress().size()==0)
            {
                result=true;
            }
        }
        assertEquals(true,result);
    }


    @Test
    void addTodo()
    {
        underTest.addTodo(testCard);
        assertEquals(1,underTest.getToDos().size());
    }

    @Test
    void assignTodo()
    {
        underTest.addTodo(testCard);
        underTest.assignTodo(testCard);
        assertEquals(1,underTest.getInProgress().size());
    }

    @Test
    void addInProgress()
    {
        underTest.addInProgress(testCard);
        assertEquals(1,underTest.getInProgress().size());
    }

    @Test
    void completeTodo()
    {
        underTest.addTodo(testCard);
        underTest.completeTodo(cardId);
        assertEquals(1,underTest.getCompleted().size());
    }

    @Test
    void completeInProgress()
    {
        underTest.addInProgress(testCard);
        underTest.completeInProgress(testCard);
        assertEquals(1,underTest.getCompleted().size());
    }

    @Test
    void removeInProgress()
    {
        underTest.addInProgress(testCard);
        underTest.removeInProgress(testCard.getCardId());
        assertEquals(0,underTest.getInProgress().size());
    }

    @Test
    void getName()
    {
        assertEquals("Test Team",underTest.getName());
    }

    @Test
    void getTeamId()
    {
        assertEquals(testUser.getTeamId(),underTest.getTeamId());
    }

    @Test
    void getTeamMembers()
    {
        assertEquals(1,underTest.getTeamMembers().size());
    }

    @Test
    void getToDos()
    {
        assertEquals(0,underTest.getToDos().size());
    }

    @Test
    void getInProgress()
    {
        assertEquals(0,underTest.getInProgress().size());
    }

    @Test
    void getCompleted()
    {
        assertEquals(0,underTest.getCompleted().size());
    }


    @Test
    void setName()
    {
        underTest.setName("New Name");
        assertEquals("New Name",underTest.getName());
    }

    @Test
    void setTeamId()
    {
        UUID newId= UUID.randomUUID();
        underTest.setTeamId(newId);
        assertEquals(newId,underTest.getTeamId());
    }

    @Disabled
    @Test
    void setTeamMembers()
    {

    }

    @Disabled
    @Test
    void setCards() {
    }
}