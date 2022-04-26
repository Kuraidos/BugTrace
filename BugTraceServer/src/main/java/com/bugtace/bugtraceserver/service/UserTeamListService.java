package com.bugtace.bugtraceserver.service;

import com.bugtace.bugtraceserver.dao.UserRepository;
import com.bugtace.bugtraceserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Service
public class UserTeamListService
{
    private final UserRepository userRepository;
    private final VerifyService verify;

    @Autowired
    UserTeamListService( UserRepository userRepository, VerifyService verify)
    {
        this.userRepository=userRepository;
        this.verify=verify;
    }

    public List<UUID> getTeamList(String email, String password)
    {
        if(verify.verifyExists(email,password))
        {
            List<UUID> result = new ArrayList<>();
            User user = userRepository.getById(email);
            result=user.getTeamIds();
            return result;
        }
        return null;
    }

}
