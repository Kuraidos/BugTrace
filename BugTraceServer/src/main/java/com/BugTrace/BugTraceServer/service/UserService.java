package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.UserDao;
import com.BugTrace.BugTraceServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    private final UserDao userDao;
    @Autowired
    public UserService(@Qualifier("FakeDB") UserDao userDao)
    {
        this.userDao=userDao;
    }
    public int addUser(User user)
    {
        return userDao.addUser(user);
    }
}
