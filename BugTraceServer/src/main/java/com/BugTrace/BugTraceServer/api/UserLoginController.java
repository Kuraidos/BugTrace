package com.BugTrace.BugTraceServer.api;

import com.BugTrace.BugTraceServer.model.User;
import com.BugTrace.BugTraceServer.service.UserLoginService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserLoginController
{
    private final UserLoginService userService;

    @Autowired
    public UserLoginController(UserLoginService userService)
    {
        this.userService=userService;
    }

    @PostMapping
    public User getUser(@RequestBody ObjectNode json)
    {
        System.out.println(userService.Login(json.get("email").asText(),json.get("password").asText()));
        return (userService.Login(json.get("email").asText(),json.get("password").asText()));
    }
}
