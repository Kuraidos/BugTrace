package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.TeamMemberRepository;
import com.BugTrace.BugTraceServer.dao.TeamRepository;
import com.BugTrace.BugTraceServer.dao.UserRepository;
import com.BugTrace.BugTraceServer.model.Team;
import com.BugTrace.BugTraceServer.model.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Id;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@DataJpaTest
class VerifyServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    private VerifyService underTest;


    private String username = "Kuraido";
    private String password = "123123";
    private String email = "serelisltu@gmail.com";
    User testUser;
    Team testTeam;


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
        boolean result = underTest.verifyPartOfTeam(email,testUser.getTeamId().toString());

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