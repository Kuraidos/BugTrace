package com.bugtace.bugtraceserver.service;

import com.bugtace.bugtraceserver.dao.CardRepository;
import com.bugtace.bugtraceserver.dao.TeamMemberRepository;
import com.bugtace.bugtraceserver.dao.TeamRepository;
import com.bugtace.bugtraceserver.dao.UserRepository;
import com.bugtace.bugtraceserver.model.Team;
import com.bugtace.bugtraceserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MainPageDataService
{
    private final CardRepository cardRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final UserRepository userRepository;
    private final VerifyService verify;

    // Checks if user exist and part of the team. If both are true return team data
    @Autowired
    public MainPageDataService(VerifyService verify,
                               TeamRepository teamRepository,
                               CardRepository cardRepository,
                               TeamMemberRepository teamMemberRepository,
                               UserRepository userRepository)
    {
        this.verify=verify;
        this.teamRepository=teamRepository;
        this.teamMemberRepository=teamMemberRepository;
        this.cardRepository=cardRepository;
        this.userRepository=userRepository;
    }

    public Team getMainPageData(String email, String password, UUID teamID)
    {
        if(teamID==null)
        {
            if(verify.verifyExists(email, password))
            {
                User user = userRepository.getById(email);
                return teamRepository.findById(user.getTeamIds().get(0)).orElse(null);
            }
        }
        else
        {
            if(verify.verifyExists(email, password) && verify.verifyPartOfTeam(email,teamID.toString()))
            {
                User user = userRepository.getById(email);
                return teamRepository.findById(teamID).orElse(null);
            }
        }

        return null;
    }

}
