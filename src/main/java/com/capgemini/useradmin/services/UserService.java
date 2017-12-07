package com.capgemini.useradmin.services;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.domain.Role;
import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.model.view.user.UserCreateViewModel;
import com.capgemini.useradmin.model.view.user.UserEditViewModel;
import com.capgemini.useradmin.model.view.user.UserViewModel;
import com.capgemini.useradmin.repository.RoleRepository;
import com.capgemini.useradmin.repository.UserRepository;
import com.capgemini.useradmin.util.UserViewModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private RoleRepository roleRepository;
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    public User getUser(long id) {

        User user = repository.findOne(id);
        if (user == null)
            throw new BadRequestException(String.format("No user with id %d.", id));
        return user;
    }

    public Page<UserViewModel> listAllByPage(Pageable pageable) {

        Page<User> pageOfDomain = repository.findAll(pageable);

        Page<UserViewModel> dtoPage = pageOfDomain.map(x -> {
            UserViewModel dto = UserViewModelMapper.userToViewMap().map(x);
            if (x.getRole() != null)
                dto.setRole(x.getRole().getName());
            return dto;
        });

        return dtoPage;
    }

    public UserViewModel get(long id) {

        User user = getUser(id);

        UserViewModel dto = UserViewModelMapper.userToViewMap().map(user);

        if (user.getRole() != null) {
            dto.setRole(user.getRole().getName());
            dto.setRoleId(user.getRole().getId());
        }
        return dto;
    }

    public void add(User entity) {

        repository.save(entity);
    }

    public void add(UserCreateViewModel model) {

        User user = UserViewModelMapper.createViewToUserMap().map(model);
        Role role = roleRepository.findOne(model.getRoleId());
        if (role == null)
            throw new BadRequestException("No Valid Role.");
        user.setRole(role);
        user.setId(0);
        repository.save(user);
    }

    public void save(UserEditViewModel model, long id) {

        User user = getUser(id);

        Role role = roleRepository.findOne(model.getRoleId());
        if (role != null && role.getId() != user.getRole().getId()) {
            user.setRole(role);
        }

        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());
        user.setEmail(model.getEmail());

        repository.save(user);
    }

    public void delete(long id) {

        getUser(id);
        repository.delete(id);
    }

    public Page<UserViewModel> search(UserViewModel view, Pageable pageable) {

        User user = UserViewModelMapper.viewToUserMap().map(view);

        if (user.getRole().getId() == 0) {
            user.setRole(null);
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withIgnorePaths("id");

        Example<User> example = Example.of(user, matcher);
        Page<User> users = repository.findAll(example, pageable);

        return users.map(e -> {
            UserViewModel model = UserViewModelMapper.userToViewMap().map(e);
            if (e.getRole() != null) {
                model.setRole(e.getRole().getName());
            }
            return model;
        });
    }

    public Iterable<User> findAll() {

        return repository.findAll();
    }
}
