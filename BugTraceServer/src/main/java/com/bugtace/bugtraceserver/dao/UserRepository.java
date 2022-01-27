package com.bugtace.bugtraceserver.dao;

import com.bugtace.bugtraceserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String>
{
}
