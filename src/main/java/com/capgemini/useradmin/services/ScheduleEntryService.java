package com.capgemini.useradmin.services;

import com.capgemini.useradmin.model.ScheduleEntry;
import com.capgemini.useradmin.repository.ScheduleEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ScheduleEntryService extends Service<ScheduleEntry> {

    @Autowired
    ScheduleEntryRepository scheduleEntryRepository;

    public ScheduleEntryService(ScheduleEntryRepository scheduleEntryRepository) {

        repository = scheduleEntryRepository;
    }
}
