package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.services.ScheduleEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/schedule")
public class ScheduleEntryController {

    ScheduleEntryService service;

    @Autowired
    public ScheduleEntryController(ScheduleEntryService service) {
        this.service = service;
    }
}