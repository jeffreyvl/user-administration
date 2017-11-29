package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.User;
import com.capgemini.useradmin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/users")
public class UserController extends Controller<User>{

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        service = userService;
    }

    @Override
    @RequestMapping(value="/all" , method = RequestMethod.GET)
    public Iterable<User> getAll() {
        Iterable<User> users = service.getAll();
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        userList.forEach(u -> {
            u.getRole().setUsers(new ArrayList<>());
            u.getDefaultEntries().forEach(d -> d.setUser(null));
            u.getScheduleEntries().forEach(s -> s.setUser(null));
        });
        return userList;
    }
}

