package com.capgemini.useradmin.controllers;

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
}