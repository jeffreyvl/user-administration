package com.capgemini.useradmin.services;


import com.capgemini.useradmin.exceptions.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T> implements IService<T> {

    PagingAndSortingRepository<T, Long> repository;

    public Iterable<T> getAll() {
        return repository.findAll();
    }

    public Page<T> listAllByPage(Pageable pageable) {

        return repository.findAll(pageable);
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

