package com.bugtace.bugtraceserver.service;

import com.bugtace.bugtraceserver.dao.CardRepository;
import com.bugtace.bugtraceserver.dao.TeamRepository;
import com.bugtace.bugtraceserver.model.Card;
import com.bugtace.bugtraceserver.model.Impact;
import com.bugtace.bugtraceserver.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CreateCardService
{

    private final VerifyService service; // Service used to check if user is valid, and part of the team
    //Daos which are used
    private final CardRepository cardRepository;
    private final TeamRepository teamRepository;
    @Autowired
    public CreateCardService(VerifyService service, TeamRepository teamRepository,CardRepository cardRepository){this.service=service;this.teamRepository=teamRepository;this.cardRepository=cardRepository;}

    public int CreateCard(String email, String password, String teamId, String username, String title, String assignTo, String priority, List<String> keywords, String description)
    {
        if(service.verifyExists(email,password) && service.verifyPartOfTeam(email,teamId))
        {
            Team team = teamRepository.findById(UUID.fromString(teamId)).orElse(null); //Look if user is part of team
            Card card = new Card(UUID.randomUUID(),title,username,java.time.LocalDate.now().toString(), Impact.valueOf(priority)); //Create new card to be inserted
            //Set additional details, which are not mandatory for card creation
            card.setKeywords(keywords);
            card.setDescription(description);
            if(!assignTo.equals(""))
            {
                card.setAssignedTo(assignTo);
                card.setDateAssigned(java.time.LocalDate.now().toString());
                team.addInProgress(card);
            }
            else
            {
                team.addTodo(card);
            }
            cardRepository.save(card); // save new card
            return 1;
        }
        return 0;
    }
}
