package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.TeamMemberRepository;
import com.BugTrace.BugTraceServer.dao.TeamRepository;
import com.BugTrace.BugTraceServer.dao.UserRepository;
import com.BugTrace.BugTraceServer.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserLoginServiceTest {

    //Daos used with the test
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private TeamRepository teamRepository;

    //List of services used
    private UserRegisterService userRegisterService;
    private UserLoginService underTest;

    //Data about user
    private String username = "Kuraido";
    private String email = "serelisltu@gmail.com";
    private String password = "123123";
    private User testUser;

    //setting up services and daos
    @BeforeEach
    void setUp()
    {
        userRegisterService=new UserRegisterService(userRepository,teamRepository,teamMemberRepository);
        testUser= new User(username,email,password);
        userRegisterService.addUser(testUser);
        underTest=new UserLoginService(userRepository);
    }

    //Delete contents of database after each
    @AfterEach
    void tearDown()
    {
        userRepository.deleteAll();
    }

    @Test
    void login()
    {
        boolean result;
        User receivedUser = underTest.Login(email,password);
        if(receivedUser!=null && receivedUser.getUsername().equals(testUser.getUsername()) && receivedUser.getEmail().equals(testUser.getEmail()) && receivedUser.getPassword().equals(testUser.getPassword()) && receivedUser.getTeamId().equals(testUser.getTeamId()))
        {
            result=true;
        }
        else
        {
            result=false;
        }
        assertEquals(true,result);
    }

    @Test
    void failLogin()
    {
        boolean result;
        User receivedUser = underTest.Login(email,email);
        if(receivedUser!=null && receivedUser.getUsername().equals(testUser.getUsername()) && receivedUser.getEmail().equals(testUser.getEmail()) && receivedUser.getPassword().equals(testUser.getPassword()) && receivedUser.getTeamId().equals(testUser.getTeamId()))
        {
            result=true;
        }
        else
        {
            result=false;
        }
        assertEquals(false,result);
    }
}