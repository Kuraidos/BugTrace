package com.BugTrace.BugTraceServer.dao;

import com.BugTrace.BugTraceServer.model.TestTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestTeamRepository extends JpaRepository<TestTeam, UUID>
{

}
