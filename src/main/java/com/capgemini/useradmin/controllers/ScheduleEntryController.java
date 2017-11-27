package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.ScheduleEntry;
import com.capgemini.useradmin.services.ScheduleEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/schedule")
public class ScheduleEntryController extends Controller<ScheduleEntry>{

    @Autowired
    ScheduleEntryService scheduleEntryService;

    public ScheduleEntryController(ScheduleEntryService scheduleEntryService) {
        service = scheduleEntryService;
    }
}