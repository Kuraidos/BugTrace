package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.CardRepository;
import com.BugTrace.BugTraceServer.dao.TeamMemberRepository;
import com.BugTrace.BugTraceServer.dao.TeamRepository;
import com.BugTrace.BugTraceServer.dao.UserRepository;
import com.BugTrace.BugTraceServer.model.Card;
import com.BugTrace.BugTraceServer.model.Impact;
import com.BugTrace.BugTraceServer.model.Team;
import com.BugTrace.BugTraceServer.model.User;
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


    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;

    private RemoveCardService underTest;
    private UserRegisterService userRegisterService;
    private CreateCardService createCardService;
    private VerifyService verifyService;

    private String username = "Kuraido";
    private String email = "serelisltu@gmail.com";
    private String password = "123123";
    private User testUser;
    private Team testTeam;



    private String title="testCard";
    private String assignTo="Kuraido";
    private Impact priority =Impact.HIGH;
    private List<String> keywords= new ArrayList<>();
    private String description="";

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