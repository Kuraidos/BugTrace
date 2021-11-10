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

    public User Login(String email, String password)
    {
        User user=userDao.getUser(email,password);
        if(user!=null)
        {
            return user;
        }
        return null;
    }
}
