package com.BugTrace.BugTraceServer.api;

import com.BugTrace.BugTraceServer.service.MainPageDataService;
import com.BugTrace.BugTraceServer.service.ModifyCardService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RequestMapping("/app/modify")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ModifyCardController
{
    private final ModifyCardService service;

    @Autowired
    public ModifyCardController(ModifyCardService service){this.service=service;}

    @PostMapping
    public int modifyCard(@RequestBody ObjectNode json)
    {
        System.out.println(json);
        List<String> keywords = new LinkedList<>();
        json.get("keywords").forEach(keyword -> keywords.add(keyword.asText()));
        return service.modifyCard(json.get("email").asText(),json.get("password").asText(),json.get("teamId").asText(),json.get("username").asText(),
                json.get("title").asText(),json.get("assignTo").asText(),json.get("priority").asText(),keywords,json.get("description").asText(),json.get("completedBy").asText(),json.get("cardId").asText());
    }
}
