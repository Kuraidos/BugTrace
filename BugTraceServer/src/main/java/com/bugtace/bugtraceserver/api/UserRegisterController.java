package com.bugtace.bugtraceserver.api;

import com.bugtace.bugtraceserver.model.User;
import com.bugtace.bugtraceserver.service.UserRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("register")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRegisterController {

    Logger logger = LoggerFactory.getLogger(UserRegisterController.class);
    private final UserRegisterService userService;

    @Autowired
    public UserRegisterController(UserRegisterService userService)
    {
        this.userService=userService;
    }

    @PostMapping
    //Takes user data and pass it to service
    public int addUser(@RequestBody User user)
    {
        logger.info("Request: "+user.toString());
        int result= userService.addUser(user);
        logger.info("Response: "+result);
        return result;
    }

}
