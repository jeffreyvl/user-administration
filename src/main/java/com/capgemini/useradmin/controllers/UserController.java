package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.view.user.UserCreateViewModel;
import com.capgemini.useradmin.model.view.user.UserEditViewModel;
import com.capgemini.useradmin.model.view.user.UserViewModel;
import com.capgemini.useradmin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Page<UserViewModel> listAllByPage(Pageable pageable) {

        return service.listAllByPage(pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(@Valid @RequestBody UserCreateViewModel model) {

        service.add(model);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserViewModel get(@PathVariable long id) {

       return service.get(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity save(@Valid @RequestBody UserEditViewModel model, @PathVariable long id) {

        service.save(model, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable long id) {

        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Page<UserViewModel> search(@Valid @RequestBody UserViewModel view, Pageable pageable) {

        return service.search(view, pageable);
    }

}


