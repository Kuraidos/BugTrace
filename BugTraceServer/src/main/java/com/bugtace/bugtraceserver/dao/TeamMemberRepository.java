package com.bugtace.bugtraceserver.dao;

import com.bugtace.bugtraceserver.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamMemberRepository extends JpaRepository<TeamMember, UUID> {
}
