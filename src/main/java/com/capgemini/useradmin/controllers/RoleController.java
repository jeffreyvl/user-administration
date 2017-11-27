package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.Role;
import com.capgemini.useradmin.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/roles/")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Role> getAll() {

        return roleRepository.findAll();
    }


    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public Role get(@PathVariable long id) {

        Role role = roleRepository.findOne(id);

        if (role == null)
            throw new BadRequestException();

        return role;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void add(@Valid @RequestBody Role role) {

        roleRepository.save(role);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void update(@Valid @RequestBody Role role) {

        roleRepository.save(role);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.DELETE)
    public void del(@PathVariable long id) {

        roleRepository.delete(id);
    }
}
