package com.capgemini.useradmin.services;

import com.capgemini.useradmin.model.domain.DefaultEntry;
import com.capgemini.useradmin.model.domain.ScheduleEntry;
import com.capgemini.useradmin.repository.DefaultEntryRepository;
import com.capgemini.useradmin.repository.ScheduleEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;

@Service
public class ScheduleEntryService {

    ScheduleEntryRepository repository;
    DefaultEntryRepository defaultEntryRepository;

    @Autowired
    public ScheduleEntryService(ScheduleEntryRepository repository, DefaultEntryRepository defaultEntryRepository) {
        this.repository = repository;
        this.defaultEntryRepository = defaultEntryRepository;
    }

    private void generate(LocalDate date) {

        DayOfWeek day = date.getDayOfWeek();
        List<DefaultEntry> defaultEntries = defaultEntryRepository.findByDay(day);
        for (DefaultEntry defaultEntry : defaultEntries) {

            ScheduleEntry scheduleEntry = new ScheduleEntry();
            scheduleEntry.setDate(date);
            scheduleEntry.setUser(defaultEntry.getUser());
            scheduleEntry.setShift(defaultEntry.getShift());

            repository.save(scheduleEntry);
        }
    }

    private void generate(int week, int year) {

        LocalDate date = LocalDate.of(year, 7, 1);
        date = date.with(WeekFields.ISO.weekOfWeekBasedYear(), week);
        date = date.with(WeekFields.ISO.dayOfWeek(), 1);

        for (int i = 0; i < 7; i++) {
            generate(date.plusDays(i));
        }
    }

    public void add(ScheduleEntry entity) {

        repository.save(entity);
    }
}
