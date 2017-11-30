package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.view.schedule.DefaultEditViewModel;
import com.capgemini.useradmin.model.view.schedule.DefaultViewModel;
import com.capgemini.useradmin.services.DefaultEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity update(@RequestBody DefaultEditViewModel model) {

        service.add(model);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public ResponseEntity save(@RequestBody DefaultEditViewModel model) {

        service.save(model);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}