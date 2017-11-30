package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.domain.DefaultEntry;
import com.capgemini.useradmin.services.DefaultEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/default-schedule")
public class DefaultEntryController  extends Controller<DefaultEntry>{

    @Autowired
    DefaultEntryService defaultEntryService;

    public DefaultEntryController(DefaultEntryService defaultEntryService) {
        service = defaultEntryService;
    }
}