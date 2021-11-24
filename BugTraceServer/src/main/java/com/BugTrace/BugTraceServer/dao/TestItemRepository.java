package com.BugTrace.BugTraceServer.dao;

import com.BugTrace.BugTraceServer.model.TestItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestItemRepository extends JpaRepository<TestItem, UUID>
{
}
