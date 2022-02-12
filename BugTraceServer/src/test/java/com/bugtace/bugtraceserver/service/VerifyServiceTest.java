package com.bugtace.bugtraceserver.service;

import com.bugtace.bugtraceserver.dao.TeamMemberRepository;
import com.bugtace.bugtraceserver.dao.TeamRepository;
import com.bugtace.bugtraceserver.dao.UserRepository;
import com.bugtace.bugtraceserver.model.Team;
import com.bugtace.bugtraceserver.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@DataJpaTest
class VerifyServiceTest {

    //Daos used with the test
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;

    //service used
    private VerifyService underTest;


    //Data about user
    private String username = "Kuraido";
    private String password = "123123";
    private String email = "serelisltu@gmail.com";
    User testUser;
    Team testTeam;


    //setting up services and daos
    @BeforeEach
    void setUp()
    {
        testUser = new User(username,email,password);
        testTeam = new Team(username+"'s team",testUser);
        userRepository.save(testUser);
        teamMemberRepository.saveAll(testTeam.getTeamMembers());
        teamRepository.save(testTeam);
        underTest = new VerifyService(teamRepository,userRepository);
    }

    //Delete contents of database after each
    @AfterEach
    void tearDown()
    {
        userRepository.deleteAll();
        teamRepository.deleteAll();
    }


    @Test
    void verifyExists()
    {
        boolean result = underTest.verifyExists(email,password);
        assertEquals(true,result);
    }



    @Test
    void verifyDoesNotExists()
    {
        boolean result = underTest.verifyExists(email,email);
        assertEquals(false,result);
    }

    @Test
    void verifyPartOfTeam()
    {
        boolean result = underTest.verifyPartOfTeam(email,testUser.getActiveTeamIds().get(0).toString());

        assertEquals(true,result);
    }

    @Test
    void verifyNoTeam()
    {
        boolean result = underTest.verifyPartOfTeam(email,UUID.randomUUID().toString());
        assertEquals(false,result);
    }

    @Test
    void verifyNotPartOfTeam()
    {
        boolean result = underTest.verifyPartOfTeam(username,testTeam.getTeamId().toString());
        assertEquals(false,result);
    }
}