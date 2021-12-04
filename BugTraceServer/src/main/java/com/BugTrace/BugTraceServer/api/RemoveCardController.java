package com.BugTrace.BugTraceServer.api;

import com.BugTrace.BugTraceServer.model.Team;
import com.BugTrace.BugTraceServer.service.MainPageDataService;
import com.BugTrace.BugTraceServer.service.RemoveCardService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/app/remove")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RemoveCardController
{

    private final RemoveCardService service;
    @Autowired
    public RemoveCardController(RemoveCardService service){this.service=service;}

    @PostMapping
    public int removeCard(@RequestBody ObjectNode json)
    {

        return service.removeCard(json.get("email").asText(),json.get("password").asText(),json.get("teamId").asText(),json.get("cardId").asText());
    }
}
