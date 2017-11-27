package com.capgemini.useradmin.services;

public interface IService<T> {

    Iterable<T> getAll();

    T get(long id);

    void delete(long id);

    void add(T entity);

    void update(T entity, long id);
}

