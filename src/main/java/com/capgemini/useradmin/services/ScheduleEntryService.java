package com.capgemini.useradmin.services;

import com.capgemini.useradmin.model.ScheduleEntry;
import com.capgemini.useradmin.repository.ScheduleEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleEntryService extends BaseService<ScheduleEntry> {

    @Autowired
    ScheduleEntryRepository scheduleEntryRepository;

    public ScheduleEntryService(ScheduleEntryRepository scheduleEntryRepository) {

        repository = scheduleEntryRepository;
    }
}
