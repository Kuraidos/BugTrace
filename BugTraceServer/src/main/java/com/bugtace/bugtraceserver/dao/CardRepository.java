package com.bugtace.bugtraceserver.dao;

import com.bugtace.bugtraceserver.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
}
