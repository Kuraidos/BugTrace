package com.bugtace.bugtraceserver.dao;

import com.bugtace.bugtraceserver.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
}
