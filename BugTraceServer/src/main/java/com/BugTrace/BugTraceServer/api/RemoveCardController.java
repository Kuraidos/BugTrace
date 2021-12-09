package com.BugTrace.BugTraceServer.api;
import com.BugTrace.BugTraceServer.service.RemoveCardService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/app/remove")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RemoveCardController
{

    Logger logger = LoggerFactory.getLogger(RemoveCardController.class);
    private final RemoveCardService service;
    @Autowired
    public RemoveCardController(RemoveCardService service){this.service=service;}

    @PostMapping
    public int removeCard(@RequestBody ObjectNode json)
    {

        logger.info("Request: "+json.toString());
        int result =service.removeCard(json.get("email").asText(),json.get("password").asText(),json.get("teamId").asText(),json.get("cardId").asText());
        logger.info("Response: "+result);
        return result;
    }
}
