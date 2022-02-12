package com.bugtace.bugtraceserver.api;

import com.bugtace.bugtraceserver.model.Team;
import com.bugtace.bugtraceserver.service.MainPageDataService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
        Team result;

        if(json.size()==2)
        {
            result =service.getMainPageData(json.get("email").asText(),json.get("password").asText(), null);
        }
        else
        {
            result =service.getMainPageData(json.get("email").asText(),json.get("password").asText(), UUID.fromString(json.get("teamID").asText()));
        }
        logger.info("Response: "+result);
        return result;
    }
}
