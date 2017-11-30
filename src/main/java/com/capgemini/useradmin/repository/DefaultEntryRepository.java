package com.capgemini.useradmin.repository;

import com.capgemini.useradmin.model.domain.DefaultEntry;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultEntryRepository extends PagingAndSortingRepository<DefaultEntry, Long> {
}
