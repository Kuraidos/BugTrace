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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ModifyCardServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;

    private VerifyService verifyService;
    private CreateCardService createCardService;
    private UserRegisterService userRegisterService;
    private ModifyCardService underTest;

    private String username = "Kuraido";
    private String email = "serelisltu@gmail.com";
    private String password = "123123";
    private User testUser;


    private String title="testCard";
    private String assignTo="Kuraido";
    private Impact priority =Impact.HIGH;
    private List<String> keywords= new ArrayList<>();
    private String description="";

    @BeforeEach
    void setUp()
    {
        verifyService= new VerifyService(teamRepository,userRepository);
        createCardService= new CreateCardService(verifyService,teamRepository,cardRepository);
        userRegisterService= new UserRegisterService(userRepository,teamRepository,teamMemberRepository);
        underTest=new ModifyCardService(verifyService,teamRepository,cardRepository,teamMemberRepository);

        testUser=new User(username,email,password);
        userRegisterService.addUser(testUser);
        createCardService.CreateCard(email,password,testUser.getTeamId().toString(),username,title,assignTo,priority.toString(),keywords,description);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void modifyCard()
    {
        Card testCard = cardRepository.findAll().get(0);
        String newTitle="NewTitle";
        underTest.modifyCard(email,password,testUser.getTeamId().toString(),username,newTitle,assignTo,priority.toString(),keywords,description,"",testCard.getCardId().toString());
        testCard = cardRepository.findById(testCard.getCardId()).orElse(null);
        assertEquals(newTitle,testCard.getTitle());
    }

    //Fail because password is wrong
    @Test
    void failModifyCard()
    {
        Card testCard = cardRepository.findAll().get(0);
        String newTitle="NewTitle";
        underTest.modifyCard(email,email,testUser.getTeamId().toString(),username,newTitle,assignTo,priority.toString(),keywords,description,"",testCard.getCardId().toString());
        testCard = cardRepository.findById(testCard.getCardId()).orElse(null);
        assertEquals(title,testCard.getTitle());
    }

    //assignTo is changed to nothing
    @Test
    void modifyToDoCard()
    {
        Card testCard = cardRepository.findAll().get(0);
        String newTitle="NewTitle";
        underTest.modifyCard(email,password,testUser.getTeamId().toString(),username,newTitle,"",priority.toString(),keywords,description,"",testCard.getCardId().toString());
        testCard = cardRepository.findById(testCard.getCardId()).orElse(null);
        assertEquals(newTitle,testCard.getTitle());
    }

    //Completed by username(Kuraido)
    @Test
    void modifyCompleteCard()
    {
        Card testCard = cardRepository.findAll().get(0);
        String newTitle="NewTitle";
        underTest.modifyCard(email,password,testUser.getTeamId().toString(),username,newTitle,"",priority.toString(),keywords,description,username,testCard.getCardId().toString());
        testCard = cardRepository.findById(testCard.getCardId()).orElse(null);
        assertEquals(newTitle,testCard.getTitle());
    }
}