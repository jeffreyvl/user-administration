package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.User;
import com.capgemini.useradmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public User get(@PathVariable long id) {
        User user = userRepository.findOne(id);
        if (user == null)
            throw new BadRequestException();
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@Valid @RequestBody User user) {

        userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@Valid @RequestBody User user) {

        userRepository.save(user);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {

        userRepository.delete(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<String> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ArrayList<String> errors = new ArrayList<>();
        for (FieldError field : fieldErrors) {
            errors.add(field.getDefaultMessage());
        }
        return errors;
    }
}

