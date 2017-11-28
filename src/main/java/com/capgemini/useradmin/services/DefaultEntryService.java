package com.capgemini.useradmin.services;

import com.capgemini.useradmin.model.DefaultEntry;
import com.capgemini.useradmin.repository.DefaultEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class DefaultEntryService extends Service<DefaultEntry> {

    @Autowired
    DefaultEntryRepository defaultEntryRepository;

    public DefaultEntryService(DefaultEntryRepository defaultEntryRepository) {

        repository = defaultEntryRepository;
    }
}