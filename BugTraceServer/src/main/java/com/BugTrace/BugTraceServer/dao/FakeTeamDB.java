package com.BugTrace.BugTraceServer.dao;

import com.BugTrace.BugTraceServer.model.Team;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Repository("FakeTeamDB")
public class FakeTeamDB implements TeamDao{
    private List<Team> teams= new ArrayList<>();
    @Override
    public int addTeam(Team team) {
        teams.add(team);
        return 1;
    }

    @Override
    public Team getTeam(UUID teamId) {
        for (Team team:teams) {
            if(team.getTeamId().equals(teamId))
            {
                return team;
            }
        }
        return null;
    }
}
