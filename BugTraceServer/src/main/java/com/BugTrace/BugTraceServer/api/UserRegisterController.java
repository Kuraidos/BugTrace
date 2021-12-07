package com.BugTrace.BugTraceServer.api;

import com.BugTrace.BugTraceServer.model.User;
import com.BugTrace.BugTraceServer.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("register")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRegisterController {
    private final UserRegisterService userService;

    @Autowired
    public UserRegisterController(UserRegisterService userService)
    {
        this.userService=userService;
    }

    @PostMapping
    public int addUser(@RequestBody User user)
    {
        System.out.println(user);
        return  userService.addUser(user);
    }

}
