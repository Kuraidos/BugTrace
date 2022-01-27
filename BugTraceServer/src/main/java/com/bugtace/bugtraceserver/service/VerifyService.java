package com.bugtace.bugtraceserver.service;

import com.bugtace.bugtraceserver.dao.TeamRepository;
import com.bugtace.bugtraceserver.dao.UserRepository;
import com.bugtace.bugtraceserver.model.Team;
import com.bugtace.bugtraceserver.model.TeamMember;
import com.bugtace.bugtraceserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VerifyService
{
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    @Autowired
    public VerifyService(TeamRepository teamRepository,UserRepository userRepository){this.teamRepository=teamRepository;this.userRepository=userRepository;}
    //Checks if user provider correct password and email
    public boolean verifyExists(String email, String password)
    {
        User user = userRepository.findById(email).orElse(null);
        if(user!=null)
        {
            if(user.getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }
    //Checks if user part of the team
    public boolean verifyPartOfTeam(String email, String teamId)
    {
        Team searchIn = teamRepository.findById(UUID.fromString(teamId)).orElse(null);
        if(searchIn==null) {return false;}
        for (TeamMember member:searchIn.getTeamMembers())
        {
            if(member.getEmail().equals(email))
            {
                return true;
            }
        }
        return false;
    }


}
