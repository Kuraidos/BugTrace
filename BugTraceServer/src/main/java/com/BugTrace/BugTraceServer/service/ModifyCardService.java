package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.TeamDao;
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
    private final TeamDao teamDao;

    @Autowired
    public ModifyCardService(@Qualifier("FakeTeamDB") TeamDao teamDao,VerifyService service) {this.service=service;this.teamDao=teamDao;}

    public int modifyCard(String email, String password, String teamId, String username, String title, String assignTo, String priority, List<String> keywords, String description, String completedBy,String cardId)
    {
        if(service.verifyExists(email,password) && service.verifyPartOfTeam(email,teamId))
        {
            Team team = teamDao.getTeam(UUID.fromString(teamId));
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
                    team.removeTodo(card.getCardId());
                    team.addTodo(card);
                }
                teamDao.removeTeam(UUID.fromString(teamId));
                teamDao.addTeam(team);
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
                teamDao.removeTeam(UUID.fromString(teamId));
                teamDao.addTeam(team);
                return 1;
            }

        }
        return 0;
    }
}
