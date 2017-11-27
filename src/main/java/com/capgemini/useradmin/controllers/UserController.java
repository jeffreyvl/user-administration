package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.User;
import com.capgemini.useradmin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/users")
public class UserController extends Controller<User>{

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        service = userService;
    }
}

