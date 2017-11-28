package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.Role;
import com.capgemini.useradmin.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/roles")
public class RoleController extends Controller<Role>{

    @Autowired
    RoleService roleService;

    public RoleController(RoleService roleService) {
        service = roleService;
    }
}