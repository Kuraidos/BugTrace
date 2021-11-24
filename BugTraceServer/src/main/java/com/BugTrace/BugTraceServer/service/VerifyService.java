package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.TeamDao;
import com.BugTrace.BugTraceServer.dao.UserDao;
import com.BugTrace.BugTraceServer.dao.UserRepository;
import com.BugTrace.BugTraceServer.model.Team;
import com.BugTrace.BugTraceServer.model.TeamMember;
import com.BugTrace.BugTraceServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VerifyService
{
    private final UserRepository userRepository;
    private final TeamDao teamDao;
    @Autowired
    public VerifyService(@Qualifier("FakeTeamDB") TeamDao teamDao,UserRepository userRepository){this.teamDao=teamDao;this.userRepository=userRepository;}
    //Need to check if user exists, then if it is part of the team
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

    public boolean verifyPartOfTeam(String email, String teamId)
    {
        Team searchIn = teamDao.getTeam(UUID.fromString(teamId));
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
