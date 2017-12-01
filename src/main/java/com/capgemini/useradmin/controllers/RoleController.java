package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.view.role.RoleCreateViewModel;
import com.capgemini.useradmin.model.view.role.RoleEditViewModel;
import com.capgemini.useradmin.model.view.role.RoleViewModel;
import com.capgemini.useradmin.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/roles")
public class RoleController {

    private RoleService service;

    @Autowired
    public RoleController(RoleService service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.GET)
    public Page<RoleViewModel> listAllByPage(Pageable pageable) {

        return service.listAllByPage(pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(@Valid @RequestBody RoleCreateViewModel model) {

        service.add(model);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RoleViewModel get(@PathVariable long id) {

        return service.get(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity save(@Valid @RequestBody RoleEditViewModel model, @PathVariable long id) {

        service.save(model, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable long id) {

        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/search/", method = RequestMethod.POST)
    public List<RoleViewModel> search(@Valid @RequestBody RoleViewModel view) {
        return service.search(view);
    }
}