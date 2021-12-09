package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.UserRepository;
import com.BugTrace.BugTraceServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService
{
    private final UserRepository userRepository;
    @Autowired
    public UserLoginService(UserRepository userRepository) {this.userRepository=userRepository;}

    public User Login(String email, String password)
    {
        User user=userRepository.findById(email).orElse(null);
        if(user!=null)
        {
            if(user.getPassword().equals(password))
            {
                return user;
            }
        }
        return null;
    }
}
