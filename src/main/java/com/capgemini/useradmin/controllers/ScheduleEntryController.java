package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.view.schedule.ScheduleViewModel;
import com.capgemini.useradmin.services.ScheduleEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/schedule")
public class ScheduleEntryController {

    ScheduleEntryService service;

    @Autowired
    public ScheduleEntryController(ScheduleEntryService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{year}/{week}", method = RequestMethod.GET)
    public List<ScheduleViewModel> get(@PathVariable int year, @PathVariable int week) {

        return service.getWeek(year,week);
    }
}