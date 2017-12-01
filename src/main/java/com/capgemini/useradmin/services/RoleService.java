package com.capgemini.useradmin.services;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.domain.Role;
import com.capgemini.useradmin.model.view.role.RoleCreateViewModel;
import com.capgemini.useradmin.model.view.role.RoleEditViewModel;
import com.capgemini.useradmin.model.view.role.RoleViewModel;
import com.capgemini.useradmin.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    private RoleRepository repository;
    private ModelMapper modelMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository) {

        repository = roleRepository;
        modelMapper = new ModelMapper();
    }

    private Role getRole(long id) {

        Role role = repository.findOne(id);
        if (role == null)
            throw new BadRequestException(String.format("No Role with id %d.", id));
        return role;
    }

    public Page<RoleViewModel> listAllByPage(Pageable pageable) {

        Page<Role> pageOfDomain = repository.findAll(pageable);

        Page<RoleViewModel> dtoPage = pageOfDomain.map(x -> {
            RoleViewModel dto = this.modelMapper.map(x, RoleViewModel.class);
            return dto;
        });
        return dtoPage;
    }

    public RoleViewModel get(long id) {

        Role role = getRole(id);
        RoleViewModel dto = modelMapper.map(role, RoleViewModel.class);

        return dto;
    }

    public void add(RoleCreateViewModel model) {

        Role role = new Role();
        role.setName(model.getName());
        repository.save(role);
    }

    public void add(Role entity) {

        repository.save(entity);
    }

    public void save(RoleEditViewModel model, long id) {

        Role role = getRole(id);

        role.setName(model.getName());

        repository.save(role);
    }

    public void delete(long id) {

        getRole(id);
        repository.delete(id);
    }

    public List<RoleViewModel> search(RoleViewModel view) {
        Role role = modelMapper.map(view, Role.class);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id", "users")
                .withIncludeNullValues();

        Example<Role> example = Example.of(role, matcher);
        Iterable<Role> roles = repository.findAll(example);

        List<RoleViewModel> list = new ArrayList<>();
        roles.forEach(e -> list.add(modelMapper.map(e, RoleViewModel.class)));

        return list;
    }

}
