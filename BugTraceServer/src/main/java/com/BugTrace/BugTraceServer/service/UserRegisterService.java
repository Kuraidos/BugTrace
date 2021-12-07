package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.*;
import com.BugTrace.BugTraceServer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class UserRegisterService
{


    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Autowired
    public UserRegisterService(UserRepository userRepository,TeamRepository teamRepository,TeamMemberRepository teamMemberRepository)
    {

        this.userRepository=userRepository;
        this.teamRepository=teamRepository;
        this.teamMemberRepository= teamMemberRepository;

    }
    public int addUser(User user)
    {

        try
        {
            Team newTeam = new Team(user.getUsername()+"'s Team",user);
            teamMemberRepository.saveAll(newTeam.getTeamMembers());
            teamRepository.save(newTeam);

            user.setTeamId(newTeam.getTeamId());
            userRepository.save(user);

            return 1;
        }
        catch (Exception e)
        {
            return 0;
        }
    }





}
