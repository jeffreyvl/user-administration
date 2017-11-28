package com.capgemini.useradmin.repository;

import com.capgemini.useradmin.model.ScheduleEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleEntryRepository extends CrudRepository<ScheduleEntry, Long> {
}
