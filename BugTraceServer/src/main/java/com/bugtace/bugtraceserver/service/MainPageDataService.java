package com.bugtace.bugtraceserver.service;

import com.bugtace.bugtraceserver.dao.CardRepository;
import com.bugtace.bugtraceserver.dao.TeamMemberRepository;
import com.bugtace.bugtraceserver.dao.TeamRepository;
import com.bugtace.bugtraceserver.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MainPageDataService
{
    private final CardRepository cardRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final VerifyService verify;

    // Checks if user exist and part of the team. If both are true return team data
    @Autowired
    public MainPageDataService(VerifyService verify, TeamRepository teamRepository,CardRepository cardRepository,TeamMemberRepository teamMemberRepository){this.verify=verify;this.teamRepository=teamRepository;this.teamMemberRepository=teamMemberRepository;this.cardRepository=cardRepository;}

    public Team getMainPageData(String email, String password, String teamId)
    {
        if(verify.verifyExists(email, password) && verify.verifyPartOfTeam(email,teamId))
        {
            return teamRepository.findById(UUID.fromString(teamId)).orElse(null);
        }
        return null;
    }

}
