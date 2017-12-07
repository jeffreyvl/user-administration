package com.capgemini.useradmin.repository;

import com.capgemini.useradmin.model.domain.ScheduleEntry;
import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.util.Shift;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleEntryRepository extends PagingAndSortingRepository<ScheduleEntry, Long> {

    Long countByDate(LocalDate date);

    List<ScheduleEntry> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);

    List<ScheduleEntry> findByUserAndDate(User user, LocalDate date);

    ScheduleEntry findByUserAndShiftAndDate(User user, Shift shift, LocalDate date);

    Long countByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
