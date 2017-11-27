package com.capgemini.useradmin.repository;

import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface Repository<T> extends CrudRepository<T, Long> {

}
