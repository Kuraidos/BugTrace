package com.BugTrace.BugTraceServer.service;

import com.BugTrace.BugTraceServer.dao.TeamDao;
import com.BugTrace.BugTraceServer.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MainPageDataService
{
    private final TeamDao teamDao;
    private final VerifyService verify;
    @Autowired
    public MainPageDataService(VerifyService verify, @Qualifier("FakeTeamDB") TeamDao teamDao){this.verify=verify;this.teamDao=teamDao;}

    public Team getMainPageData(String email, String password, String teamId)
    {
        if(verify.verifyExists(email, password) && verify.verifyPartOfTeam(email,teamId))
        {
            return teamDao.getTeam(UUID.fromString(teamId));
        }
        return null;
    }

}
