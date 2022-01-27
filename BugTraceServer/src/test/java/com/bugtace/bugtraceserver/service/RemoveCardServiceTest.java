package com.bugtace.bugtraceserver.service;

import com.bugtace.bugtraceserver.dao.CardRepository;
import com.bugtace.bugtraceserver.dao.TeamMemberRepository;
import com.bugtace.bugtraceserver.dao.TeamRepository;
import com.bugtace.bugtraceserver.dao.UserRepository;
import com.bugtace.bugtraceserver.model.Card;
import com.bugtace.bugtraceserver.model.Impact;
import com.bugtace.bugtraceserver.model.Team;
import com.bugtace.bugtraceserver.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class RemoveCardServiceTest {


    //Daos used with the test
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;

    //List of services used
    private RemoveCardService underTest;
    private UserRegisterService userRegisterService;
    private CreateCardService createCardService;
    private VerifyService verifyService;

    //Data about user
    private String username = "Kuraido";
    private String email = "serelisltu@gmail.com";
    private String password = "123123";
    private User testUser;
    private Team testTeam;

    //Data about card
    private String title="testCard";
    private String assignTo="Kuraido";
    private Impact priority =Impact.HIGH;
    private List<String> keywords= new ArrayList<>();
    private String description="";

    //setting up services and daos
    @BeforeEach
    void setUp()
    {
        verifyService = new VerifyService(teamRepository,userRepository);
        userRegisterService = new UserRegisterService(userRepository,teamRepository,teamMemberRepository);
        createCardService = new CreateCardService(verifyService,teamRepository,cardRepository);
        underTest = new RemoveCardService(verifyService,teamRepository,cardRepository);

        testUser= new User(username,email,password);
        userRegisterService.addUser(testUser);
        createCardService.CreateCard(email,password,testUser.getTeamId().toString(),username,title,assignTo,priority.toString(),keywords,description);
    }

    //Delete contents of database after each
    @AfterEach
    void tearDown()
    {
        teamRepository.deleteAll();
        teamMemberRepository.deleteAll();
        userRepository.deleteAll();
        cardRepository.deleteAll();
    }

    @Test
    void removeCard()
    {
        boolean result = false;
        testTeam= teamRepository.findById(testUser.getTeamId()).orElse(null);
        if(testTeam!=null)
        {
            List<Card> allCards= new ArrayList<>();
            allCards.addAll(testTeam.getToDos());
            allCards.addAll(testTeam.getCompleted());
            allCards.addAll(testTeam.getInProgress());
            underTest.removeCard(email,password,testUser.getTeamId().toString(),allCards.get(0).getCardId().toString());
            testTeam= teamRepository.findById(testUser.getTeamId()).orElse(null);
            if(testTeam!=null && testTeam.getToDos().size()==0 && testTeam.getInProgress().size()==0 && testTeam.getCompleted().size()==0)
            {
                result=true;
            }
        }
        assertEquals(true,result);
    }

    //Wrong password so fail
    @Test
    void failRemoveCard()
    {
        boolean result = false;
        testTeam= teamRepository.findById(testUser.getTeamId()).orElse(null);
        if(testTeam!=null)
        {
            List<Card> allCards= new ArrayList<>();
            allCards.addAll(testTeam.getToDos());
            allCards.addAll(testTeam.getCompleted());
            allCards.addAll(testTeam.getInProgress());
            underTest.removeCard(email,email,testUser.getTeamId().toString(),allCards.get(0).getCardId().toString());
            testTeam= teamRepository.findById(testUser.getTeamId()).orElse(null);
            if(testTeam!=null && testTeam.getToDos().size()==0 && testTeam.getInProgress().size()==0 && testTeam.getCompleted().size()==0)
            {
                result=true;
            }
        }
        assertEquals(false,result);
    }
}