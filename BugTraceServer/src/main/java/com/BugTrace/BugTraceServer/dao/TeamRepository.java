package com.BugTrace.BugTraceServer.dao;

import com.BugTrace.BugTraceServer.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
}
