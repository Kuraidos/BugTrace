package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.CardRepository;
import com.BugTrace.BugTraceServer.dao.TeamDao;
import com.BugTrace.BugTraceServer.dao.TeamMemberRepository;
import com.BugTrace.BugTraceServer.dao.TeamRepository;
import com.BugTrace.BugTraceServer.model.Card;
import com.BugTrace.BugTraceServer.model.Impact;
import com.BugTrace.BugTraceServer.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ModifyCardService
{
    private final VerifyService service;
    private final CardRepository cardRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Autowired
    public ModifyCardService(VerifyService service,TeamRepository teamRepository,CardRepository cardRepository,TeamMemberRepository teamMemberRepository) {this.service=service;
        this.teamRepository=teamRepository;this.teamMemberRepository=teamMemberRepository;this.cardRepository=cardRepository;}

    public int modifyCard(String email, String password, String teamId, String username, String title, String assignTo, String priority, List<String> keywords, String description, String completedBy,String cardId)
    {
        if(service.verifyExists(email,password) && service.verifyPartOfTeam(email,teamId))
        {
            Team team = teamRepository.findById(UUID.fromString(teamId)).orElse(null);
            Card card = new Card(UUID.fromString(cardId),title,username,java.time.LocalDate.now().toString(), Impact.valueOf(priority));
            card.setKeywords(keywords);
            card.setDescription(description);
            Card tempCard =team.getToDos().stream().filter(cardToFind -> cardToFind.getCardId().equals(card.getCardId())).findAny().orElse(null);
            if(tempCard!=null)
            {
                if(!assignTo.equals(""))
                {
                    card.setAssignedTo(assignTo);
                    card.setDateAssigned(java.time.LocalDate.now().toString());
                    team.assignTodo(card);
                }
                else
                {
                    team.removeCard(card.getCardId());
                    team.addTodo(card);
                }
                cardRepository.saveAll(team.getInProgress());
                cardRepository.saveAll(team.getCompleted());
                cardRepository.saveAll(team.getToDos());
                teamMemberRepository.saveAll(team.getTeamMembers());
                teamRepository.save(team);
                return 1;
            }
            tempCard=team.getInProgress().stream().filter(cardToFind -> cardToFind.getCardId().equals(card.getCardId())).findAny().orElse(null);
            if(tempCard!=null)
            {
                card.setDateAssigned(tempCard.getDateAssigned());
                card.setAssignedTo(tempCard.getAssignedTo());
                if(!completedBy.equals(""))
                {
                    card.setCompletedBy(completedBy);
                    card.setDateCompleted(java.time.LocalDate.now().toString());
                    team.completeInProgress(card);
                }
                else
                {
                    team.removeInProgress(card.getCardId());
                    team.addInProgress(card);
                }
                teamMemberRepository.saveAll(team.getTeamMembers());
                cardRepository.saveAll(team.getInProgress());
                cardRepository.saveAll(team.getCompleted());
                cardRepository.saveAll(team.getToDos());
                teamRepository.save(team);
                return 1;
            }
        }
        return 0;
    }
}
