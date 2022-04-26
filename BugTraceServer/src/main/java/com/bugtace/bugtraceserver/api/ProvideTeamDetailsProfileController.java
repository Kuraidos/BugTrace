package com.bugtace.bugtraceserver.api;

import com.bugtace.bugtraceserver.model.TeamDetailsProfile;
import com.bugtace.bugtraceserver.service.ProvideTeamDetailsProfileService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("teamDetailsProfile")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProvideTeamDetailsProfileController
{
    private final ProvideTeamDetailsProfileService provideTeamDetailsService;

    @Autowired
    public ProvideTeamDetailsProfileController(ProvideTeamDetailsProfileService provideTeamDetailsService)
    {
        this.provideTeamDetailsService=provideTeamDetailsService;
    }

    @PostMapping
    public List<TeamDetailsProfile> getTeamDetails(@RequestBody ObjectNode json)
    {
        return (provideTeamDetailsService.getDetails(json.get("email").asText(), json.get("password").asText()));
    }
}
