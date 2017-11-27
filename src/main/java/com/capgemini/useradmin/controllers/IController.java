package com.capgemini.useradmin.controllers;

public interface IController<T> {

    Iterable<T> getAll();

    T get(long id);

    void add(T entity);

    void update(T entity, long id);

    void delete(long id);
}
