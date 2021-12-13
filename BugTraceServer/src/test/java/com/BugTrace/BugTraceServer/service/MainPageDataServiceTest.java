package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.CardRepository;
import com.BugTrace.BugTraceServer.dao.TeamMemberRepository;
import com.BugTrace.BugTraceServer.dao.TeamRepository;
import com.BugTrace.BugTraceServer.dao.UserRepository;
import com.BugTrace.BugTraceServer.model.Team;
import com.BugTrace.BugTraceServer.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MainPageDataServiceTest {

    //Daos used with the test
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private UserRepository userRepository;

    //List of services used
    private VerifyService verify;
    private MainPageDataService underTest;
    private UserRegisterService userRegisterService;

    //Data about user
    private String username="Kuraido";
    private String email="serelisltu@gmail.com";
    private String password="123123";
    private User testUser;
    private Optional<Team> testTeam;


    //setting up services and daos
    @BeforeEach
    void setUp()
    {
        verify = new VerifyService(teamRepository,userRepository);
        underTest = new MainPageDataService(verify,teamRepository,cardRepository,teamMemberRepository);
        userRegisterService= new UserRegisterService(userRepository,teamRepository,teamMemberRepository);

        testUser= new User(username,email,password);
        userRegisterService.addUser(testUser);
        testTeam=teamRepository.findById(testUser.getTeamId());

    }

    //Delete contents of database after each
    @AfterEach
    void tearDown()
    {
        cardRepository.deleteAll();
        teamRepository.deleteAll();
        teamMemberRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getMainPageData()
    {

        boolean result =false;
        Team received = underTest.getMainPageData(email,password,testUser.getTeamId().toString());
        if(received!=null)
        {
            result=true;
        }
        assertEquals(true,result);
    }
    @Test
    void failGetMainPageData()
    {

        boolean result =false;
        Team received = underTest.getMainPageData(email,email,testUser.getTeamId().toString());
        if(received!=null)
        {
            result=true;
        }
        assertEquals(false,result);
    }
}