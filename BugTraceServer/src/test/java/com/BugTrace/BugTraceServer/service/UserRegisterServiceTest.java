package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.TeamMemberRepository;
import com.BugTrace.BugTraceServer.dao.TeamRepository;
import com.BugTrace.BugTraceServer.dao.UserRepository;
import com.BugTrace.BugTraceServer.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRegisterServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    private UserRegisterService underTest;
    private String username="Kuraido";
    private String email="serelisltu@gmail.com";
    private String password ="123123";


    @BeforeEach
    void setUp()
    {
        underTest=new UserRegisterService(userRepository,teamRepository,teamMemberRepository);
    }

    @AfterEach
    void tearDown()
    {
        userRepository.deleteAll();
        teamRepository.deleteAll();
        teamMemberRepository.deleteAll();
    }

    @Test
    void addUser()
    {
        User testUser= new User(username,email,password);
        int result = underTest.addUser(testUser);
        assertEquals(1,result);
    }

    @Test
    void failAddUser()
    {
        User testUser= null;
        int result = underTest.addUser(testUser);
        assertEquals(0,result);
    }
}