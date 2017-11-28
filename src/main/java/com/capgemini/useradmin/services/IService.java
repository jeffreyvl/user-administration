package com.capgemini.useradmin.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T> {

    Iterable<T> getAll();

    Page<T> listAllByPage(Pageable pageable);

    T get(long id);

    void delete(long id);

    void add(T entity);

    void update(T entity, long id);
}

