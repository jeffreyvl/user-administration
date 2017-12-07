package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.view.schedule.*;
import com.capgemini.useradmin.services.ScheduleEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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
    public List<ScheduleViewModel> getWeek(@PathVariable int year, @PathVariable int week) {

        return service.getWeek(year, week);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ScheduleDayViewModel> getDay(@RequestParam(value = "date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return service.getDay(date);
    }

    @RequestMapping(value="hours/{year}/{month}", method = RequestMethod.GET)
    public List<HoursViewModel> getHours(@PathVariable int year, @PathVariable int month) {

        return service.getHours(year, month);
    }
    @RequestMapping(value = "/reset/{year}/{week}", method = RequestMethod.POST)
    ResponseEntity generateWeek(@PathVariable int year, @PathVariable int week) {

        service.generateWeek(year, week);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value= "/reset", method = RequestMethod.POST)
    ResponseEntity generateDay(@RequestParam(value="date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        service.generate(date);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value= "week", method = RequestMethod.PUT)
    ResponseEntity saveWeek(@RequestBody List<ScheduleEditViewModel> model) {

        service.saveWeek(model);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="", method=RequestMethod.PUT)
    ResponseEntity saveDay(@RequestBody List<ScheduleDayEditViewModel> model) {

        service.saveDay(model);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}