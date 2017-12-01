package com.capgemini.useradmin.services;

import com.capgemini.useradmin.model.domain.DefaultEntry;
import com.capgemini.useradmin.model.domain.ScheduleEntry;
import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.model.view.schedule.ScheduleDayEditViewModel;
import com.capgemini.useradmin.model.view.schedule.ScheduleDayViewModel;
import com.capgemini.useradmin.model.view.schedule.ScheduleEditViewModel;
import com.capgemini.useradmin.model.view.schedule.ScheduleViewModel;
import com.capgemini.useradmin.repository.DefaultEntryRepository;
import com.capgemini.useradmin.repository.ScheduleEntryRepository;
import com.capgemini.useradmin.util.HelperMethods;
import com.capgemini.useradmin.util.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class ScheduleEntryService {

    ScheduleEntryRepository repository;
    DefaultEntryRepository defaultEntryRepository;
    UserService userService;

    @Autowired
    public ScheduleEntryService(ScheduleEntryRepository repository, DefaultEntryRepository defaultEntryRepository, UserService userService) {
        this.repository = repository;
        this.defaultEntryRepository = defaultEntryRepository;
        this.userService = userService;
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

        Iterable<User> userList = userService.findAll();

        List<ScheduleViewModel> listModel = new ArrayList<>();
        for (User user : userList) {

            List<ScheduleEntry> scheduleEntries = repository.findByUserAndDateBetween(user, date, date.plusDays(6));

            ScheduleViewModel model = new ScheduleViewModel(date);

            model.setFirstName(user.getFirstName());
            model.setLastName(user.getLastName());
            model.setUserId(user.getId());

            for (ScheduleEntry scheduleEntry : scheduleEntries)
                model.add(scheduleEntry);

            listModel.add(model);
        }

        return listModel;
    }

    public List<ScheduleDayViewModel> getDay(LocalDate date) {

        if (repository.countByDate(date) == 0)
            generate(date);

        Iterable<User> userList = userService.findAll();
        List<ScheduleDayViewModel> listModel = new ArrayList<>();
        for (User user : userList) {

            List<ScheduleEntry> scheduleEntries = repository.findByUserAndDate(user, date);
            ScheduleDayViewModel model = new ScheduleDayViewModel(date);

            model.setFirstName(user.getFirstName());
            model.setLastName(user.getLastName());
            model.setUserId(user.getId());

            for (ScheduleEntry scheduleEntry : scheduleEntries)
                model.add(scheduleEntry);
            listModel.add(model);
        }
        return listModel;
    }

    public void saveWeek(List<ScheduleEditViewModel> listModel) {

        for (ScheduleEditViewModel model : listModel) {

            User user = userService.getUser(model.getUserId());

            Map<LocalDate, Map<Shift, Boolean>> elements = model.getScheduleEntries();

            for (Map.Entry<LocalDate, Map<Shift, Boolean>> date : elements.entrySet()) {

                for (Map.Entry<Shift, Boolean> shift : date.getValue().entrySet()) {

                    update(shift,date.getKey(),user);
                }
            }
        }
    }

    public void saveDay(List<ScheduleDayEditViewModel> listModel) {

        for (ScheduleDayEditViewModel model : listModel) {

            User user = userService.getUser(model.getUserId());

            Map<Shift, Boolean> elements = model.getScheduleEntries();


            for (Map.Entry<Shift, Boolean> shift : elements.entrySet()) {

                update(shift,model.getDate(),user);
            }
        }
    }

    private void update(Map.Entry<Shift, Boolean> shift, LocalDate date, User user) {

        if (shift.getValue()) {
            ScheduleEntry entry = new ScheduleEntry();
            entry.setDate(date);
            entry.setShift(shift.getKey());
            entry.setUser(user);

            repository.save(entry);
        }

        if (!shift.getValue()) {
            ScheduleEntry scheduleEntry = repository.findByUserAndShiftAndDate(user, shift.getKey(), date);
            if (scheduleEntry != null)
                repository.delete(scheduleEntry);
        }
    }


    public void add(ScheduleEntry entity) {

        repository.save(entity);
    }
}
