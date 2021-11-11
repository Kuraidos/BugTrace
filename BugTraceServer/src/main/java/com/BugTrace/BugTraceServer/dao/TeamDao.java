package com.BugTrace.BugTraceServer.dao;

import com.BugTrace.BugTraceServer.model.Team;

import java.util.UUID;

public interface TeamDao
{
    int addTeam(Team team);
    Team getTeam(UUID teamId);
    int removeTeam(UUID teamId);
}
