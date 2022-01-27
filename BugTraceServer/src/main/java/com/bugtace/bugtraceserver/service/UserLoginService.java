package com.bugtace.bugtraceserver.service;

import com.bugtace.bugtraceserver.dao.UserRepository;
import com.bugtace.bugtraceserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService
{
    private final UserRepository userRepository;
    @Autowired
    public UserLoginService(UserRepository userRepository) {this.userRepository=userRepository;}

    //Checks if user password nad email match, if does allow login
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
