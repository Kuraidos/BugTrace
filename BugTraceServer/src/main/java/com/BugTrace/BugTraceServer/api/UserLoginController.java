package com.BugTrace.BugTraceServer.api;
import com.BugTrace.BugTraceServer.model.User;
import com.BugTrace.BugTraceServer.service.UserLoginService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserLoginController
{
    Logger logger = LoggerFactory.getLogger(UserLoginController.class);
    private final UserLoginService userService;

    @Autowired
    public UserLoginController(UserLoginService userService)
    {
        this.userService=userService;
    }

    //Pass data to service
    @PostMapping
    public User getUser(@RequestBody ObjectNode json)
    {
        logger.info("Request: "+json.toString());
        User result =userService.Login(json.get("email").asText(),json.get("password").asText());
        logger.info("Response: "+result);
        return result;
    }
}
