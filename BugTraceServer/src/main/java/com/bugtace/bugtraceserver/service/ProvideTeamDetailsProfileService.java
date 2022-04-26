package com.bugtace.bugtraceserver.service;

import com.bugtace.bugtraceserver.dao.TeamRepository;
import com.bugtace.bugtraceserver.dao.UserRepository;
import com.bugtace.bugtraceserver.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProvideTeamDetailsProfileService {

    private final VerifyService verify;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Autowired
    ProvideTeamDetailsProfileService(VerifyService verify, TeamRepository teamRepository, UserRepository userRepository)
    {
        this.verify=verify;
        this.teamRepository=teamRepository;
        this.userRepository=userRepository;
    }

    public List<TeamDetailsProfile> getDetails(String email, String password)
    {
        if(verify.verifyExists(email,password))
        {   List<TeamDetailsProfile> result=new ArrayList<>();
            User user=userRepository.getById(email);
            List<UUID> teamIds=user.getTeamIds();

            for (UUID teamId:teamIds)
            {
                if(verify.verifyPartOfTeam(email,teamId.toString()))
                {
                    Team team=teamRepository.getById(teamId);
                    TeamMember userTeamDetails= team.getTeamMembers().stream().filter(x ->x.getEmail().equals(email)).findFirst().orElse(null);
                    if(userTeamDetails!=null)
                    {
                        TeamDetailsProfile userTeamData = new TeamDetailsProfile();
                        userTeamData.setNameOfTeam(team.getName());
                        userTeamData.setLevel(userTeamDetails.getLevel());
                        result.add(userTeamData);
                    }
                }
            }
            return result;
        }

        return null;
    }
}
