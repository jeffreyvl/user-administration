package com.capgemini.useradmin.services;

import com.capgemini.useradmin.model.domain.DefaultEntry;
import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.model.view.schedule.DefaultEditViewModel;
import com.capgemini.useradmin.model.view.schedule.DefaultViewModel;
import com.capgemini.useradmin.repository.DefaultEntryRepository;
import com.capgemini.useradmin.util.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@Service
public class DefaultEntryService {

    DefaultEntryRepository repository;
    UserService userService;

    @Autowired
    public DefaultEntryService(DefaultEntryRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public void add(DefaultEntry entity) {
        repository.save(entity);
    }

    public DefaultViewModel get(long id) {

        User user = userService.getUser(id);

        List<DefaultEntry> defaultEntries = repository.findByUser(user);

        DefaultViewModel defaultViewModel = new DefaultViewModel();
        defaultViewModel.setUserId(user.getId());
        defaultViewModel.setFirstName(user.getFirstName());
        defaultViewModel.setLastName(user.getLastName());

        for (DefaultEntry defaultEntry : defaultEntries) {
            defaultViewModel.add(defaultEntry);
        }

        return defaultViewModel;
    }

    public void save(DefaultEditViewModel model) {

        User user = userService.getUser(model.getUserId());

        Map<DayOfWeek, Map<Shift, Boolean>> elements = model.getDefaultEntries();
        for (Map.Entry<DayOfWeek, Map<Shift, Boolean>> day : elements.entrySet()) {

            for (Map.Entry<Shift, Boolean> shift : day.getValue().entrySet()) {

                if (shift.getValue()) {
                    DefaultEntry entry = new DefaultEntry();
                    entry.setDay(day.getKey());
                    entry.setShift(shift.getKey());
                    entry.setUser(user);

                    repository.save(entry);
                }

                if (!shift.getValue()) {
                    DefaultEntry defaultEntry = repository.findByUserAndShiftAndDay(user, shift.getKey(), day.getKey());
                    if (defaultEntry != null)
                        repository.delete(defaultEntry.getId());
                }
            }
        }
    }
}
