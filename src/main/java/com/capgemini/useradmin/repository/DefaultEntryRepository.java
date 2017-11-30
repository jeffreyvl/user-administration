package com.capgemini.useradmin.repository;

import com.capgemini.useradmin.model.domain.DefaultEntry;
import com.capgemini.useradmin.model.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefaultEntryRepository extends PagingAndSortingRepository<DefaultEntry, Long> {

    List<DefaultEntry> findByUser(User user);
}
