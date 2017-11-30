package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.view.role.RoleCreateViewModel;
import com.capgemini.useradmin.model.view.role.RoleEditViewModel;
import com.capgemini.useradmin.model.view.role.RoleViewModel;
import com.capgemini.useradmin.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<RoleViewModel> getAll(Pageable pageable) {

        return service.listAllByPage(pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@Valid @RequestBody RoleCreateViewModel model) {

        service.add(model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RoleViewModel get(@PathVariable long id) {

        return service.get(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@Valid @RequestBody RoleEditViewModel model, @PathVariable long id) {

        service.save(model, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        service.delete(id);
    }
}