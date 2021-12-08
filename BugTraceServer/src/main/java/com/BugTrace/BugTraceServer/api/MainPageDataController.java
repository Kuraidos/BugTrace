package com.BugTrace.BugTraceServer.api;

import com.BugTrace.BugTraceServer.model.Team;
import com.BugTrace.BugTraceServer.service.MainPageDataService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("app")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainPageDataController
{
    Logger logger = LoggerFactory.getLogger(MainPageDataController.class);
    private final MainPageDataService service;
    @Autowired
    public MainPageDataController(MainPageDataService service){this.service=service;}

    @PostMapping
    public Team getMainPageData(@RequestBody ObjectNode json)
    {
        logger.info("Request: "+json.toString());
        Team result =service.getMainPageData(json.get("email").asText(),json.get("password").asText(),json.get("teamId").asText());
        logger.info("Response: "+result);
        return result;
    }
}
