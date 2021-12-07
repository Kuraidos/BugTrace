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
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CreateCardServiceTest {


    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;

    private VerifyService verify;
    private UserRegisterService registerService;
    private CreateCardService underTest;

    private String username="Kuraido";
    private String email="serelisltu@gmail.com";
    private String password ="123123";
    private User testUser;

    // String assignTo, String priority, List<String> keywords, String description
    private String title="testCard";
    private String assignTo="Kuraido";
    private Impact priority =Impact.HIGH;
    private List<String> keywords= new ArrayList<>();
    private String description="";

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
        Team testTeam= teamRepository.findById(testUser.getTeamId()).orElse(null);
        if(testTeam!=null)
        {
            underTest.CreateCard(email,password,testUser.getTeamId().toString(),username,title,assignTo,priority.toString(),keywords,description);
            testTeam= teamRepository.findById(testUser.getTeamId()).orElse(null);
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
        Team testTeam= teamRepository.findById(testUser.getTeamId()).orElse(null);
        if(testTeam!=null)
        {
            underTest.CreateCard(email,password,testUser.getTeamId().toString(),username,title,"",priority.toString(),keywords,description);
            testTeam= teamRepository.findById(testUser.getTeamId()).orElse(null);
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
        Team testTeam= teamRepository.findById(testUser.getTeamId()).orElse(null);
        if(testTeam!=null)
        {
            underTest.CreateCard(email,email,testUser.getTeamId().toString(),username,title,"",priority.toString(),keywords,description);
            testTeam= teamRepository.findById(testUser.getTeamId()).orElse(null);
            List<Card> testCards = testTeam.getToDos();
            if(testCards.size()>1){
                Card testCard =testCards.get(0);
                result=testCard.getTitle().equals(title);
            }

        }
        assertEquals(false,result);
    }
}
