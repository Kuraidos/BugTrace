package com.bugtace.bugtraceserver.service;
import com.bugtace.bugtraceserver.dao.TeamMemberRepository;
import com.bugtace.bugtraceserver.dao.TeamRepository;
import com.bugtace.bugtraceserver.dao.UserRepository;
import com.bugtace.bugtraceserver.model.Team;
import com.bugtace.bugtraceserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    //Simply adds user to database
    //Make in front end when returned 0, print error that email is taken.
    public int addUser(User user)
    {

        try
        {
            User checkUser = userRepository.findById(user.getEmail()).orElse(null);
            if(checkUser!=null) {return 0;}
            Team newTeam = new Team(user.getUsername()+"'s Team",user);
            teamMemberRepository.saveAll(newTeam.getTeamMembers());
            teamRepository.save(newTeam);

            user.getActiveTeamIds().set(0,newTeam.getTeamId());
            userRepository.save(user);

            return 1;
        }
        catch (Exception e)
        {
            return 0;
        }
    }





}
