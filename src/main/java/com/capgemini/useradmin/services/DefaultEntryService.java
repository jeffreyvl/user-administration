package com.capgemini.useradmin.services;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.domain.DefaultEntry;
import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.model.view.schedule.DefaultViewModel;
import com.capgemini.useradmin.repository.DefaultEntryRepository;
import com.capgemini.useradmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        for (DefaultEntry defaultEntry: defaultEntries) {
            defaultViewModel.add(defaultEntry);
        }

        return defaultViewModel;
    }
}
