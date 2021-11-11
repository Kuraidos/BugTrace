package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.TeamDao;
import com.BugTrace.BugTraceServer.model.Card;
import com.BugTrace.BugTraceServer.model.Impact;
import com.BugTrace.BugTraceServer.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CreateCardService
{
    private final VerifyService service;
    private final TeamDao teamDao;
    @Autowired
    public CreateCardService(VerifyService service, @Qualifier("FakeTeamDB") TeamDao teamDao){this.service=service;this.teamDao=teamDao;}

    public int CreateCard(String email, String password, String teamId, String username, String title, String assignTo, String priority, List<String> keywords, String description)
    {
        if(service.verifyExists(email,password) && service.verifyPartOfTeam(email,teamId))
        {
            Team team = teamDao.getTeam(UUID.fromString(teamId));
            Card card = new Card(UUID.randomUUID(),title,username,java.time.LocalDate.now().toString(), Impact.valueOf(priority));
            card.setKeywords(keywords);
            card.setDescription(description);
            if(!assignTo.equals(""))
            {
                card.setAssignedTo(assignTo);
                team.addInProgress(card);
            }
            else
            {
                team.addTodo(card);
            }
            return 1;
        }
        return 0;
    }
}
