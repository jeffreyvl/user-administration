package com.capgemini.useradmin.services;

import com.capgemini.useradmin.model.domain.DefaultEntry;
import com.capgemini.useradmin.repository.DefaultEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultEntryService {

    DefaultEntryRepository repository;

    @Autowired
    public DefaultEntryService(DefaultEntryRepository repository) {
        this.repository = repository;
    }

    public void add(DefaultEntry entity) {
        repository.save(entity);
    }
}
