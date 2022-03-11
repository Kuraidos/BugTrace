package com.bugtace.bugtraceserver.api;

import com.bugtace.bugtraceserver.service.UserRegisterService;
import com.bugtace.bugtraceserver.service.UserTeamListService;
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

    private final UserTeamListService userTeamListService;

    @Autowired
    public TeamListController(UserTeamListService userTeamListService)
    {
        this.userTeamListService=userTeamListService;
    }

    @PostMapping
    public List<UUID>[] addUser(@RequestBody ObjectNode json)
    {
        return (userTeamListService.getTeamList(json.get("email").asText(), json.get("password").asText()));
    }
}
