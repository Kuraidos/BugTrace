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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private TeamRepository teamRepository;
    private UserRegisterService userRegisterService;
    private UserLoginService underTest;

    private String username = "Kuraido";
    private String email = "serelisltu@gmail.com";
    private String password = "123123";
    private User testUser;

    @BeforeEach
    void setUp()
    {
        userRegisterService=new UserRegisterService(userRepository,teamRepository,teamMemberRepository);
        testUser= new User(username,email,password);
        userRegisterService.addUser(testUser);
        underTest=new UserLoginService(userRepository);
    }

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