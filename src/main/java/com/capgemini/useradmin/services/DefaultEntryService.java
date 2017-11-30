package com.capgemini.useradmin.services;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.domain.DefaultEntry;
import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.model.view.schedule.DefaultEditViewModel;
import com.capgemini.useradmin.model.view.schedule.DefaultViewModel;
import com.capgemini.useradmin.repository.DefaultEntryRepository;
import com.capgemini.useradmin.repository.UserRepository;
import com.capgemini.useradmin.util.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@Service
public class DefaultEntryService {

    DefaultEntryRepository repository;
    UserRepository userRepository;


    @Autowired
    public DefaultEntryService(DefaultEntryRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public void add(DefaultEntry entity) {
        repository.save(entity);
    }

    public DefaultViewModel get(long id) {

        User user = userRepository.findOne(id);

        if (user == null)
            throw new BadRequestException("User not found.");

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

        User user = userRepository.findOne(model.getUserId());
        if (user == null) {
            throw new BadRequestException();
        }

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
            }
        }
    }

    public void add(DefaultEditViewModel model) {

        save(model);
    }
}
