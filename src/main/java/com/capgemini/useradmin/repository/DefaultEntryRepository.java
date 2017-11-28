package com.capgemini.useradmin.repository;

import com.capgemini.useradmin.model.DefaultEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultEntryRepository extends CrudRepository<DefaultEntry, Long>{
}
