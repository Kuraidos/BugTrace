package com.bugtace.bugtraceserver.api;

import com.bugtace.bugtraceserver.service.UserRegisterService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("teamList")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeamListController
{

    private final UserRegisterService userTeamListService;

    @Autowired
    public TeamListController(UserRegisterService userTeamListService)
    {
        this.userTeamListService=userTeamListService;
    }

    @PostMapping
    public List<UUID>[] addUser(@RequestBody ObjectNode json)
    {
       List<UUID>[] result = new List[2];
       result[0]= new ArrayList<UUID>();
       result[0].add(UUID.randomUUID());
       result[0].add(UUID.randomUUID());
       result[1]= new ArrayList<UUID>();
       result[1].add(UUID.randomUUID());
       result[1].add(UUID.randomUUID());
       return result;
    }
}
