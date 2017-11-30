package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.domain.view.UserCreateViewModel;
import com.capgemini.useradmin.model.domain.view.UserEditViewModel;
import com.capgemini.useradmin.model.domain.view.UserViewModel;
import com.capgemini.useradmin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/users")
public class UserController {

    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Page<UserViewModel> listAllByPage(Pageable pageable) {

        return service.listAllByPage(pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@Valid @RequestBody UserCreateViewModel model) {

        service.add(model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserViewModel get(@PathVariable long id) {

       return service.get(id);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public void update(@Valid @RequestBody UserEditViewModel model, @PathVariable long id) {

        service.save(model, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        service.delete(id);
    }
}


