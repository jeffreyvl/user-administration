package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.services.Service;
import com.capgemini.useradmin.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

public abstract class Controller<T> implements IController<T>{

    @Autowired
    private Service<T> service;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<T> getAll() {

        return service.getAll();
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public T get(@PathVariable long id) {

        T entity = service.get(id);

        if (entity == null)
            throw new BadRequestException();

        return entity;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@Valid @RequestBody T entity) {

        service.add(entity);
    }

    @RequestMapping(value = "/{id}/",method = RequestMethod.PUT)
    public void update(@Valid @RequestBody T entity, @PathVariable long id) {

        service.update(entity, id);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        service.delete(id);
    }
}





