package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.DefaultEntry;
import com.capgemini.useradmin.repository.DefaultEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("api/default/")
public class DefaultEntryController {

    @Autowired
    DefaultEntryRepository defaultEntryRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<DefaultEntry> getAll() {
        return defaultEntryRepository.findAll();
    }


    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public DefaultEntry get(@PathVariable long id) {
        DefaultEntry defaultEntry = defaultEntryRepository.findOne(id);
        if (defaultEntry == null)
            throw new BadRequestException();
        return defaultEntry;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void add(@Valid @RequestBody DefaultEntry defaultEntry) {

        defaultEntryRepository.save(defaultEntry);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void update(@Valid @RequestBody DefaultEntry defaultEntry) {

        defaultEntryRepository.save(defaultEntry);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.DELETE)
    public void del(@PathVariable long id) {

        defaultEntryRepository.delete(id);
    }
}
