package com.bugtace.bugtraceserver.dao;

import com.bugtace.bugtraceserver.model.Team;

import java.util.UUID;

public interface TeamDao
{
    int addTeam(Team team);
    Team getTeam(UUID teamId);
    int removeTeam(UUID teamId);
}
