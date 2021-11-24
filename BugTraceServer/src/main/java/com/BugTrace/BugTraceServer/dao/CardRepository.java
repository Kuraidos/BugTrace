package com.BugTrace.BugTraceServer.dao;

import com.BugTrace.BugTraceServer.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
}
