package com.bugtace.bugtraceserver.dao;

import com.bugtace.bugtraceserver.model.User;

public interface UserDao {
    int addUser(User user);
    User getUser(String email, String password);

}
