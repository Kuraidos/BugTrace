package com.bugtace.bugtraceserver.service;

import com.bugtace.bugtraceserver.dao.CardRepository;
import com.bugtace.bugtraceserver.dao.TeamMemberRepository;
import com.bugtace.bugtraceserver.dao.TeamRepository;
import com.bugtace.bugtraceserver.dao.UserRepository;
import com.bugtace.bugtraceserver.model.Card;
import com.bugtace.bugtraceserver.model.Impact;
import com.bugtace.bugtraceserver.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ModifyCardServiceTest {

    //Daos used with the test
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;

    //List of services used
    private VerifyService verifyService;
    private CreateCardService createCardService;
    private UserRegisterService userRegisterService;
    private ModifyCardService underTest;

    //Data about user
    private String username = "Kuraido";
    private String email = "serelisltu@gmail.com";
    private String password = "123123";
    private User testUser;

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
        verifyService= new VerifyService(teamRepository,userRepository);
        createCardService= new CreateCardService(verifyService,teamRepository,cardRepository);
        userRegisterService= new UserRegisterService(userRepository,teamRepository,teamMemberRepository);
        underTest=new ModifyCardService(verifyService,teamRepository,cardRepository,teamMemberRepository);

        testUser=new User(username,email,password);
        userRegisterService.addUser(testUser);
        createCardService.CreateCard(email,password,testUser.getTeamIds().get(0).toString(),username,title,assignTo,priority.toString(),keywords,description);

    }


    @Test
    void modifyCard()
    {
        Card testCard = cardRepository.findAll().get(0);
        String newTitle="NewTitle";
        underTest.modifyCard(email,password,testUser.getTeamIds().get(0).toString(),username,newTitle,assignTo,priority.toString(),keywords,description,"",testCard.getCardId().toString());
        testCard = cardRepository.findById(testCard.getCardId()).orElse(null);
        assertEquals(newTitle,testCard.getTitle());
    }

    //Fail because password is wrong
    @Test
    void failModifyCard()
    {
        Card testCard = cardRepository.findAll().get(0);
        String newTitle="NewTitle";
        underTest.modifyCard(email,email,testUser.getTeamIds().get(0).toString(),username,newTitle,assignTo,priority.toString(),keywords,description,"",testCard.getCardId().toString());
        testCard = cardRepository.findById(testCard.getCardId()).orElse(null);
        assertEquals(title,testCard.getTitle());
    }

    //assignTo is changed to nothing
    @Test
    void modifyToDoCard()
    {
        Card testCard = cardRepository.findAll().get(0);
        String newTitle="NewTitle";
        underTest.modifyCard(email,password,testUser.getTeamIds().get(0).toString(),username,newTitle,"",priority.toString(),keywords,description,"",testCard.getCardId().toString());
        testCard = cardRepository.findById(testCard.getCardId()).orElse(null);
        assertEquals(newTitle,testCard.getTitle());
    }

    //Completed by username(Kuraido)
    @Test
    void modifyCompleteCard()
    {
        Card testCard = cardRepository.findAll().get(0);
        String newTitle="NewTitle";
        underTest.modifyCard(email,password,testUser.getTeamIds().get(0).toString(),username,newTitle,"",priority.toString(),keywords,description,username,testCard.getCardId().toString());
        testCard = cardRepository.findById(testCard.getCardId()).orElse(null);
        assertEquals(newTitle,testCard.getTitle());
    }
}