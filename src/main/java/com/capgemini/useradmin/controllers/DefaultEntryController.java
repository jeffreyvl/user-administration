package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.view.schedule.DefaultEditViewModel;
import com.capgemini.useradmin.model.view.schedule.DefaultViewModel;
import com.capgemini.useradmin.services.DefaultEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/default-schedule")
public class DefaultEntryController {

    private DefaultEntryService service;

    @Autowired
    public DefaultEntryController(DefaultEntryService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DefaultViewModel get(@PathVariable long id) {

        return service.get(id);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void update(@RequestBody DefaultEditViewModel model) {

        service.add(model);
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public void save(@RequestBody DefaultEditViewModel model) {

        service.save(model);
    }

}