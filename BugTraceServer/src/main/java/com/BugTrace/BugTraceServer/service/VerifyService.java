package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.TeamDao;
import com.BugTrace.BugTraceServer.dao.UserDao;
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

    private final TeamDao teamDao;
    private final UserDao userDao;
    @Autowired
    public VerifyService(@Qualifier("FakeTeamDB") TeamDao teamDao, @Qualifier("FakeDB") UserDao userDao){this.teamDao=teamDao;this.userDao=userDao;}
    //Need to check if user exists, then if it is part of the team
    public boolean verifyExists(String email, String password)
    {
        User user =userDao.getUser(email,password);
        if(user!=null)
        {
            return true;
        }
        return false;
    }

    public boolean verifyPartOfTeam(String email, String teamId)
    {
        Team searchIn = teamDao.getTeam(UUID.fromString(teamId));
        if(searchIn==null) {return false;}
        for (TeamMember member:searchIn.getTeamMembers())
        {
            if(member.getUser().getEmail().equals(email))
            {
                return true;
            }
        }
        return false;
    }
}