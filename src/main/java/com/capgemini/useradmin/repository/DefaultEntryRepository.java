package com.capgemini.useradmin.repository;

import com.capgemini.useradmin.model.domain.DefaultEntry;
import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.util.Shift;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface DefaultEntryRepository extends PagingAndSortingRepository<DefaultEntry, Long> {

    List<DefaultEntry> findByUser(User user);

    List<DefaultEntry> findByDay(DayOfWeek day);

    DefaultEntry findByUserAndShiftAndDay(User user, Shift shift, DayOfWeek day);

    @Query(value = "SELECT id FROM default_entries WHERE user_id = ?1 AND shift = ?2 AND day = ?3", nativeQuery= true )
    Long findIdByUserAndDayAndShift (User user, Shift shift, DayOfWeek day);
}
