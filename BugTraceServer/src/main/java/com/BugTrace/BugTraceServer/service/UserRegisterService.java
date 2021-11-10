package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.TeamDao;
import com.BugTrace.BugTraceServer.dao.UserDao;
import com.BugTrace.BugTraceServer.model.Team;
import com.BugTrace.BugTraceServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserRegisterService
{
    private final UserDao userDao;
    private final TeamDao teamDao;
    @Autowired
    public UserRegisterService(@Qualifier("FakeDB") UserDao userDao, @Qualifier("FakeTeamDB") TeamDao teamDao)
    {
        this.userDao=userDao;
        this.teamDao=teamDao;
    }
    public int addUser(User user)
    {
        Team newTeam = new Team(user.getUsername()+"'s Team",user);
        teamDao.addTeam(newTeam);
        return userDao.addUser(user);
    }
}
