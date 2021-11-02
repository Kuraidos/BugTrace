package com.BugTrace.BugTraceServer.dao;

import com.BugTrace.BugTraceServer.model.User;

public interface UserDao {
    int addUser(User user);
    User getUser(String username, String password);

}
