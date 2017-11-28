package com.capgemini.useradmin.repository;

import com.capgemini.useradmin.model.ScheduleEntry;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleEntryRepository extends PagingAndSortingRepository<ScheduleEntry, Long> {
}
