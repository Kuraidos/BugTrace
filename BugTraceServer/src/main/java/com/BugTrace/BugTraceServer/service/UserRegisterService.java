package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.*;
import com.BugTrace.BugTraceServer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class UserRegisterService
{

    private final VerifyService service;
    private final UserRepository userRepository;
    private final TestTeamRepository testTeamRepository;
    private final TestItemRepository testItemRepository;
    private final CardRepository cardRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Autowired
    public UserRegisterService(TestTeamRepository testTeamRepository, TestItemRepository testItemRepository,UserRepository userRepository,VerifyService service, CardRepository cardRepository
    ,TeamRepository teamRepository,TeamMemberRepository teamMemberRepository)
    {
        this.testTeamRepository=testTeamRepository;
        this.testItemRepository=testItemRepository;
        this.userRepository=userRepository;
        this.service=service;
        this.cardRepository=cardRepository;
        this.teamRepository=teamRepository;
        this.teamMemberRepository= teamMemberRepository;

    }
    public int addUser(User user)
    {
        testCard();
        testTeamMember();
        testTeam();
        Team newTeam = new Team(user.getUsername()+"'s Team",user);
        teamRepository.save(newTeam);
        try
        {
            user.setTeamId(newTeam.getTeamId());
            userRepository.save(user);
            System.out.println(userRepository.getById(user.getEmail()));
            return 1;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public void doThis(User user)
    {
        TestTeam testTeam = new TestTeam("name",user);

        TestItem testItem = new TestItem();
        testItem.setItemId(UUID.randomUUID());
        testItem.setName("Why");
        testItemRepository.save(testItem);

        TestItem testItem2 = new TestItem();
        testItem2.setItemId(UUID.randomUUID());
        testItem2.setName("Why2");
        testItemRepository.save(testItem2);


        List<TestItem> items = testTeam.getTestItems();
        items.add(testItem);
        items.add(testItem2);
        testTeam.setTestItems(items);
        testTeamRepository.save(testTeam);

        System.out.println(testTeamRepository.getById(testTeam.getTeamId()));
    }


    public void testTeam()
    {
        Team team = new Team("Test Team",new User("Kuraido","serelisltu@gmail.com","123123"));
        team.setTeamId(UUID.randomUUID());

        Card card1 = new Card(UUID.randomUUID(),"testTeamCard1","Kuraido","Today",Impact.HIGH);
        card1.setTypeOfCard(TypeOfCard.COMPLETED);
        Card card2 = new Card(UUID.randomUUID(),"testTeamCard2","Kuraido2","Today2",Impact.HIGH);
        card2.setTypeOfCard(TypeOfCard.COMPLETED);

        List<Card> cardList = new LinkedList<>();
        cardList.add(card1);
        cardList.add(card2);

        team.setCards(cardList);

        teamMemberRepository.saveAll(team.getTeamMembers());
        cardRepository.saveAll(team.getCards());
        teamRepository.save(team);
        System.out.println("Team Working");
    }

    public void testCard()
    {
        Card card1 = new Card(UUID.randomUUID(),"test","Kuraido","Today",Impact.HIGH);
        card1.setTypeOfCard(TypeOfCard.COMPLETED);
        Card card2 = new Card(UUID.randomUUID(),"test2","Kuraido2","Today2",Impact.HIGH);
        card2.setTypeOfCard(TypeOfCard.COMPLETED);
        cardRepository.save(card1);
        cardRepository.save(card2);
        card1 = cardRepository.findById(card1.getCardId()).orElse(null);
        card2 = cardRepository.findById(card2.getCardId()).orElse(null);
        if(card1!=null && card2!=null)
        {
            System.out.println("Card Working");
        }
    }

    public void testTeamMember()
    {

        User user1 = new User("Kuraido","serelisltu@gmail.com","123123");
        User user2 = new User("Kuraido2","serelisltu@gmail.com2","123123");
        TeamMember teamMember1 = new TeamMember(user1,Level.MANAGER);
        TeamMember teamMember2 = new TeamMember(user2,Level.MEMBER);

        teamMemberRepository.save(teamMember1);
        teamMemberRepository.save(teamMember2);

        teamMember1=teamMemberRepository.findById(teamMember1.getMemberId()).orElse(null);
        teamMember2=teamMemberRepository.findById(teamMember2.getMemberId()).orElse(null);
        if(teamMember1!=null && teamMember2!=null)
        {
            System.out.println("Team Member working");
        }
    }

}
