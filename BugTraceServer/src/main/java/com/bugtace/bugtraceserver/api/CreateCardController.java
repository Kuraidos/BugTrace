package com.bugtace.bugtraceserver.api;

import com.bugtace.bugtraceserver.service.CreateCardService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;



@RequestMapping("/app/create")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CreateCardController
{
    Logger logger = LoggerFactory.getLogger(CreateCardController.class);
    private final CreateCardService service;
    @Autowired
    CreateCardController(CreateCardService service) {this.service=service;}


    @PostMapping
    public int createCard(@RequestBody ObjectNode json)
    {
        List<String> keywords = new LinkedList<>();
        json.get("keywords").forEach(keyword -> keywords.add(keyword.asText()));
        int result =service.CreateCard(
                json.get("email").asText(),
                json.get("password").asText(),
                json.get("teamId").asText(),
                json.get("username").asText(),
                json.get("title").asText(),
                json.get("assignTo").asText(),
                json.get("priority").asText(),
                keywords,
                json.get("description").asText());
        logger.info("Request: {} => Response: {}",json,result);
        return result;
    }


}
