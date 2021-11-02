package com.BugTrace.BugTraceServer.dao;


import com.BugTrace.BugTraceServer.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("FakeDB")
public class FakeDB implements UserDao
{
    private static List<User> fakeDB= new ArrayList<>();
    @Override
    public int addUser(User user)
    {
        fakeDB.add(user);
        return 1;
    }

    @Override
    public User getUser(String username, String password)
    {
        for (User user:fakeDB)
        {
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                return user;
            }
        }
        return null;
    }


}
