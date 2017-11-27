package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.DefaultEntry;
import com.capgemini.useradmin.repository.DefaultEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("api/default-schedule/")
public class DefaultEntryController {

    @Autowired
    private DefaultEntryRepository defaultEntryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<DefaultEntry> getAll() {

        return defaultEntryRepository.findAll();
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public DefaultEntry get(@PathVariable long id) {

        DefaultEntry defaultEntry = defaultEntryRepository.findOne(id);

        if (defaultEntry == null)
            throw new BadRequestException();

        return defaultEntry;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@Valid @RequestBody DefaultEntry defaultEntry) {

        defaultEntryRepository.save(defaultEntry);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@Valid @RequestBody DefaultEntry defaultEntry) {

        defaultEntryRepository.save(defaultEntry);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        defaultEntryRepository.delete(id);
    }
}