package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.TeamDao;
import com.BugTrace.BugTraceServer.dao.UserDao;
import com.BugTrace.BugTraceServer.model.MainPageData;
import com.BugTrace.BugTraceServer.model.Team;
import com.BugTrace.BugTraceServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService
{
    private final UserDao userDao;
    private final TeamDao teamDao;
    @Autowired
    public UserLoginService(@Qualifier("FakeDB") UserDao userDao,@Qualifier("FakeTeamDB") TeamDao teamDao) {this.userDao=userDao;this.teamDao=teamDao;}

    public MainPageData getMainPageData(String email, String password)
    {
        User user=userDao.getUser(email,password);
        if(user!=null)
        {
            if(user.getTeamId()!=null)
            {
                return (new MainPageData(user,teamDao.getTeam(user.getTeamId())));
            }
            else
            {
                Team newTeam = new Team(user.getUsername()+"'s Team",user);
                teamDao.addTeam(newTeam);
                return (getMainPageData(email,password));
            }
        }
        return null;
    }
}
