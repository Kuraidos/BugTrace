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
class CreateCardServiceTest {


    //Daos used with the test
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;

    //list of services used
    private VerifyService verify;
    private UserRegisterService registerService;
    private CreateCardService underTest;

    //info about user
    private String username="Kuraido";
    private String email="serelisltu@gmail.com";
    private String password ="123123";
    private User testUser;

    //Info about card
    private String title="testCard";
    private String assignTo="Kuraido";
    private Impact priority =Impact.HIGH;
    private List<String> keywords= new ArrayList<>();
    private String description="";

    //setting up services and daos
    @BeforeEach
    void setUp()
    {
        testUser = new User(username,email,password);
        verify=new VerifyService(teamRepository,userRepository);
        registerService= new UserRegisterService(userRepository,teamRepository,teamMemberRepository);
        registerService.addUser(testUser);
        underTest= new CreateCardService(verify,teamRepository,cardRepository);
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void createInProgressCard()
    {
        boolean result = false;
        Team testTeam= teamRepository.findById(testUser.getActiveTeamIds().get(0)).orElse(null);
        if(testTeam!=null)
        {
            underTest.CreateCard(email,password,testUser.getActiveTeamIds().get(0).toString(),username,title,assignTo,priority.toString(),keywords,description);
            testTeam= teamRepository.findById(testUser.getActiveTeamIds().get(0)).orElse(null);
            List<Card> testCards = testTeam.getInProgress();
            Card testCard =testCards.get(0);
            result=testCard.getTitle().equals(title);

        }
        assertEquals(true,result);
    }

    @Test
    void createToDOCard()
    {
        boolean result = false;
        Team testTeam= teamRepository.findById(testUser.getActiveTeamIds().get(0)).orElse(null);
        if(testTeam!=null)
        {
            underTest.CreateCard(email,password,testUser.getActiveTeamIds().get(0).toString(),username,title,"",priority.toString(),keywords,description);
            testTeam= teamRepository.findById(testUser.getActiveTeamIds().get(0)).orElse(null);
            List<Card> testCards = testTeam.getToDos();
            Card testCard =testCards.get(0);
            result=testCard.getTitle().equals(title);

        }
        assertEquals(true,result);
    }

    @Test
    void failCreateCard()
    {
        boolean result = false;
        Team testTeam= teamRepository.findById(testUser.getActiveTeamIds().get(0)).orElse(null);
        if(testTeam!=null)
        {
            underTest.CreateCard(email,email,testUser.getActiveTeamIds().get(0).toString(),username,title,"",priority.toString(),keywords,description);
            testTeam= teamRepository.findById(testUser.getActiveTeamIds().get(0)).orElse(null);
            List<Card> testCards = testTeam.getToDos();
            if(testCards.size()>1){
                Card testCard =testCards.get(0);
                result=testCard.getTitle().equals(title);
            }

        }
        assertEquals(false,result);
    }
}
