package com.capgemini.useradmin.services;

import com.capgemini.useradmin.model.domain.DefaultEntry;
import com.capgemini.useradmin.model.domain.ScheduleEntry;
import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.model.view.schedule.ScheduleDayViewModel;
import com.capgemini.useradmin.model.view.schedule.ScheduleViewModel;
import com.capgemini.useradmin.repository.DefaultEntryRepository;
import com.capgemini.useradmin.repository.ScheduleEntryRepository;
import com.capgemini.useradmin.repository.UserRepository;
import com.capgemini.useradmin.util.HelperMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Service
public class ScheduleEntryService {

    ScheduleEntryRepository repository;
    DefaultEntryRepository defaultEntryRepository;
    UserRepository userRepository;

    @Autowired
    public ScheduleEntryService(ScheduleEntryRepository repository, DefaultEntryRepository defaultEntryRepository, UserRepository userRepository) {
        this.repository = repository;
        this.defaultEntryRepository = defaultEntryRepository;
        this.userRepository = userRepository;
    }


    public void generate(LocalDate date) {

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

    public void generateWeek(int year, int week) {

        LocalDate date = HelperMethods.getDateFirstDay(year, week);

        if (date.getDayOfWeek() != DayOfWeek.MONDAY) {
            TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
            date.with(fieldISO, 1);
        }

        for (int i = 0; i < 7; i++) {
            generate(date.plusDays(i));
        }
    }

    public void generateWeekMissingDays(LocalDate date) {

        if (date.getDayOfWeek() != DayOfWeek.MONDAY) {
            TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
            date.with(fieldISO, 1);
        }

        for (int i = 0; i < 7; i++) {
            LocalDate temp = date.plusDays(i);
            if (repository.countByDate(temp) == 0)
                generate(temp);
        }
    }

    public List<ScheduleViewModel> getWeek(int year, int week) {

        LocalDate date = HelperMethods.getDateFirstDay(year, week);
        generateWeekMissingDays(date);

       Iterable<User> userList = userRepository.findAll();

        List<ScheduleViewModel> listModel = new ArrayList<>();
        for (User user: userList) {

            List<ScheduleEntry> scheduleEntries = repository.findByUserAndDateBetween(user,date,date.plusDays(6));

            ScheduleViewModel model = new ScheduleViewModel(date);

            model.setFirstName(user.getFirstName());
            model.setLastName(user.getLastName());
            model.setUserId(user.getId());

            for (ScheduleEntry scheduleEntry: scheduleEntries)
                model.add(scheduleEntry);
            listModel.add(model);
        }

        return listModel;
    }

    public List<ScheduleDayViewModel> getDay (LocalDate date) {

        if (repository.countByDate(date) == 0)
            generate(date);

        Iterable<User> userList = userRepository.findAll();
        List<ScheduleDayViewModel> listModel = new ArrayList<>();
        for (User user: userList) {

            List<ScheduleEntry> scheduleEntries = repository.findByUserAndDate(user, date);
            ScheduleDayViewModel model = new ScheduleDayViewModel(date);

            model.setFirstName(user.getFirstName());
            model.setLastName(user.getLastName());
            model.setUserId(user.getId());

            for (ScheduleEntry scheduleEntry: scheduleEntries)
                model.add(scheduleEntry);
            listModel.add(model);
        }
        return listModel;
    }

    public void add(ScheduleEntry entity) {

        repository.save(entity);
    }
}
