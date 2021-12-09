package com.BugTrace.BugTraceServer.service;
import com.BugTrace.BugTraceServer.dao.TeamMemberRepository;
import com.BugTrace.BugTraceServer.dao.TeamRepository;
import com.BugTrace.BugTraceServer.dao.UserRepository;
import com.BugTrace.BugTraceServer.model.Team;
import com.BugTrace.BugTraceServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService
{


    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Autowired
    public UserRegisterService(UserRepository userRepository,TeamRepository teamRepository,TeamMemberRepository teamMemberRepository)
    {

        this.userRepository=userRepository;
        this.teamRepository=teamRepository;
        this.teamMemberRepository= teamMemberRepository;

    }
    public int addUser(User user)
    {

        try
        {
            Team newTeam = new Team(user.getUsername()+"'s Team",user);
            teamMemberRepository.saveAll(newTeam.getTeamMembers());
            teamRepository.save(newTeam);

            user.setTeamId(newTeam.getTeamId());
            userRepository.save(user);

            return 1;
        }
        catch (Exception e)
        {
            return 0;
        }
    }





}
