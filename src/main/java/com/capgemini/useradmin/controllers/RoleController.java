package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.Role;
import com.capgemini.useradmin.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Role> getAll() {

        return roleRepository.findAll();
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public Role get(@PathVariable long id) {

        Role role = roleRepository.findOne(id);

        if (role == null)
            throw new BadRequestException();

        return role;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@Valid @RequestBody Role role) {

        roleRepository.save(role);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@Valid @RequestBody Role role) {

        roleRepository.save(role);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        roleRepository.delete(id);
    }
}
