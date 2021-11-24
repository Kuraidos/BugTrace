package com.BugTrace.BugTraceServer.dao;

import com.BugTrace.BugTraceServer.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamMemberRepository extends JpaRepository<TeamMember, UUID> {
}
