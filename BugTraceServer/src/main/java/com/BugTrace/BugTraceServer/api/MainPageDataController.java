package com.BugTrace.BugTraceServer.api;

import com.BugTrace.BugTraceServer.model.Team;
import com.BugTrace.BugTraceServer.service.MainPageDataService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RequestMapping("app")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainPageDataController
{
    private final MainPageDataService service;
    @Autowired
    public MainPageDataController(MainPageDataService service){this.service=service;}

    @PostMapping
    public Team getMainPageData(@RequestBody ObjectNode json)
    {
        System.out.println("here");
        System.out.println(service.getMainPageData(json.get("email").asText(),json.get("password").asText(),json.get("teamId").asText()));
        return service.getMainPageData(json.get("email").asText(),json.get("password").asText(),json.get("teamId").asText());
    }
}
