package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.CardRepository;
import com.BugTrace.BugTraceServer.dao.TeamRepository;
import com.BugTrace.BugTraceServer.model.Team;
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
