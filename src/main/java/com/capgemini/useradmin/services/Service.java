package com.capgemini.useradmin.services;


import com.capgemini.useradmin.exceptions.BadRequestException;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Service
public abstract class Service<T> implements IService<T> {

    CrudRepository<T, Long> repository;

    public Iterable<T> getAll() {
        return repository.findAll();
    }

    public T get(long id) {

        T entity = repository.findOne(id);

        if (entity == null)
            throw new BadRequestException();

        return entity;
    }

    public void delete(long id) {

        repository.delete(id);
    }

    public void add(T entity) {

        repository.save(entity);
    }

    public void update(T entity, long id) {

        repository.save(entity);
    }
}

