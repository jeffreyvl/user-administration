package com.capgemini.useradmin.services;

        import com.capgemini.useradmin.model.domain.ScheduleEntry;
        import com.capgemini.useradmin.repository.ScheduleEntryRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

@Service
public class ScheduleEntryService {

    ScheduleEntryRepository repository;

    @Autowired
    public ScheduleEntryService(ScheduleEntryRepository repository) {
        this.repository = repository;
    }

    public void add(ScheduleEntry entity) {

        repository.save(entity);
    }
}
