package com.BugTrace.BugTraceServer.api;

import com.BugTrace.BugTraceServer.model.User;
import com.BugTrace.BugTraceServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("register")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRegisterController {
    private final UserService userService;

    @Autowired
    public UserRegisterController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping
    public int addUser(@RequestBody User user)
    {
        System.out.println(user);
        return  userService.addUser(user);
    }

    @GetMapping
    public int displayUser(@RequestBody User user)
    {
        return 45;
    }
}
