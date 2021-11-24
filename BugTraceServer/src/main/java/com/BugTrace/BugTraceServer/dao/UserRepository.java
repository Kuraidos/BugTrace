package com.BugTrace.BugTraceServer.dao;

import com.BugTrace.BugTraceServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String>
{
}
