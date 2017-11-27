package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.ScheduleEntry;
import com.capgemini.useradmin.repository.ScheduleEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("api/schedule/")
public class ScheduleEntryController {

    @Autowired
    private ScheduleEntryRepository scheduleEntryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<ScheduleEntry> getAll() {
        return scheduleEntryRepository.findAll();
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public ScheduleEntry get(@PathVariable long id) {
        ScheduleEntry scheduleEntry = scheduleEntryRepository.findOne(id);
        if (scheduleEntry == null)
            throw new BadRequestException();
        return scheduleEntry;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@Valid @RequestBody ScheduleEntry scheduleEntry) {

        scheduleEntryRepository.save(scheduleEntry);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@Valid @RequestBody ScheduleEntry scheduleEntry) {

        scheduleEntryRepository.save(scheduleEntry);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        scheduleEntryRepository.delete(id);
    }
}
