package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.UserDao;
import com.BugTrace.BugTraceServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService
{
    private final UserDao userDao;
    @Autowired
    public UserLoginService(@Qualifier("FakeDB") UserDao userDao) {this.userDao=userDao;}

    public int getUser(String username,String password)
    {
        if(userDao.getUser(username,password)!=null)
        {
            return 1;
        }
        return 0;
    }
}
