package com.bugtace.bugtraceserver.service;

import com.bugtace.bugtraceserver.dao.CardRepository;
import com.bugtace.bugtraceserver.dao.TeamMemberRepository;
import com.bugtace.bugtraceserver.dao.TeamRepository;
import com.bugtace.bugtraceserver.model.Card;
import com.bugtace.bugtraceserver.model.Impact;
import com.bugtace.bugtraceserver.model.Team;
import com.bugtace.bugtraceserver.model.TypeOfCard;
import org.springframework.beans.factory.annotation.Autowired;
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
            Card tempCard =team.getToDos().stream().filter(cardToFind -> cardToFind.getCardId().equals(UUID.fromString(cardId))).findAny().orElse(team.getInProgress().stream().filter(cardToFind -> cardToFind.getCardId().equals(UUID.fromString(cardId))).findAny().orElse(null));
            if(tempCard!=null)
            {
                //Creates new card and sets data
                Card card = new Card(UUID.fromString(cardId),title,username,tempCard.getDateCreated(), Impact.valueOf(priority));
                card.setKeywords(keywords);
                card.setDescription(description);
                card.setAssignedTo(assignTo);
                card.setCompletedBy(completedBy);
                card.setCreator(tempCard.getCreator());
                card.setDateCreated(tempCard.getDateCreated());
                team.removeCard(UUID.fromString(cardId));

                //Decided from which data is missing, if it is part of T0DO, INPROGRESS or COMPLETED
                if(assignTo.equals("") && completedBy.equals(""))
                {
                    card.setDateAssigned("");
                    team.addTodo(card);
                }
                else if(completedBy.equals("") && !assignTo.equals(""))
                {
                    card.setDateAssigned(java.time.LocalDate.now().toString());
                    card.setAssignedTo(assignTo);
                    team.addInProgress(card);
                }
                else if(!completedBy.equals(""))
                {
                    card.setCompletedBy(completedBy);
                    card.setDateCompleted(java.time.LocalDate.now().toString());
                    card.setTypeOfCard(TypeOfCard.COMPLETED);
                    team.addCard(card);
                }
                //Saves data
                cardRepository.saveAll(team.getInProgress());
                cardRepository.saveAll(team.getCompleted());
                cardRepository.saveAll(team.getToDos());
                teamMemberRepository.saveAll(team.getTeamMembers());
                teamRepository.save(team);
                return 1;
            }
        }
        return 0;
    }
}
