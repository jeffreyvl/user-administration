package com.capgemini.useradmin.services;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.domain.Role;
import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.model.view.user.UserCreateViewModel;
import com.capgemini.useradmin.model.view.user.UserEditViewModel;
import com.capgemini.useradmin.model.view.user.UserViewModel;
import com.capgemini.useradmin.repository.RoleRepository;
import com.capgemini.useradmin.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private RoleRepository roleRepository;
    private UserRepository repository;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        modelMapper = new ModelMapper();
    }

    public Page<UserViewModel> listAllByPage(Pageable pageable) {

        Page<User> pageOfDomain = repository.findAll(pageable);
        Page<UserViewModel> dtoPage = pageOfDomain.map(x -> {
            UserViewModel dto = this.modelMapper.map(x, UserViewModel.class);
            if (x.getRole() != null)
                dto.setRole(x.getRole().getName());
            return dto;
        });

        return dtoPage;
    }

    public UserViewModel get(long id) {

        User user = repository.findOne(id);
        UserViewModel dto = modelMapper.map(user, UserViewModel.class);
        if (user.getRole() != null)
            dto.setRole(user.getRole().getName());
        return dto;
    }

    public void add(User entity) {

        repository.save(entity);
    }

    public void add(UserCreateViewModel model) {

        User user = modelMapper.map(model, User.class);
        Role role = roleRepository.findOne(model.getRoleId());
        if (role == null)
            throw new BadRequestException("No Valid Role.");
        user.setRole(role);
        user.setId(0);
        repository.save(user);
    }

    public void save(UserEditViewModel model, long id) {

        User user = repository.findOne(model.getId());
        if (user == null || model.getId() != id)
            throw new BadRequestException();

        Role role = roleRepository.findOne(model.getRoleId());
        if (role != null && role.getId() != model.getRoleId())
            user.setRole(role);

        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());
        user.setEmail(model.getEmail());

        repository.save(user);
    }

    public void delete(long id) {

        repository.delete(id);
    }

}
