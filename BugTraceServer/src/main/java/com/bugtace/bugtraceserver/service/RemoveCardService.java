package com.bugtace.bugtraceserver.service;

import com.bugtace.bugtraceserver.dao.CardRepository;
import com.bugtace.bugtraceserver.dao.TeamRepository;
import com.bugtace.bugtraceserver.model.Team;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RemoveCardService
{
    private final VerifyService verify;
    private final TeamRepository teamRepository;
    private final CardRepository cardRepository;
    public RemoveCardService(VerifyService verify,TeamRepository teamRepository,CardRepository cardRepository){this.verify=verify;this.teamRepository=teamRepository;this.cardRepository=cardRepository;}
    public int removeCard(String email, String password ,String teamId,String cardId)
    {
        //check if request is valid and part of the team, if he is removes card
        if(verify.verifyExists(email,password) && verify.verifyPartOfTeam(email,teamId))
        {
            Team workingTeam = teamRepository.findById(UUID.fromString(teamId)).orElse(null);
            if(workingTeam!=null)
            {
                int result = workingTeam.removeCard(UUID.fromString(cardId));
                teamRepository.save(workingTeam);
                return result;
            }
        }
        return 0;
    }
}
