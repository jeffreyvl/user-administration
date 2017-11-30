package com.capgemini.useradmin.services;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.domain.Role;
import com.capgemini.useradmin.model.view.role.RoleCreateViewModel;
import com.capgemini.useradmin.model.view.role.RoleEditViewModel;
import com.capgemini.useradmin.model.view.role.RoleViewModel;
import com.capgemini.useradmin.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleService{

    private RoleRepository repository;
    private ModelMapper modelMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository) {

        repository = roleRepository;
        modelMapper = new ModelMapper();
    }
    public Page<RoleViewModel> listAllByPage(Pageable pageable) {

        Page<Role> pageOfDomain = repository.findAll(pageable);
        Page<RoleViewModel> dtoPage = pageOfDomain.map(x -> modelMapper.map(x, RoleViewModel.class));

        return dtoPage;
    }

    public RoleViewModel get(long id) {

        Role user = repository.findOne(id);
        RoleViewModel dto = modelMapper.map(user, RoleViewModel.class);

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

        Role role = repository.findOne(model.getId());
        if (role == null || model.getId() != id)
            throw new BadRequestException();

        role.setName(model.getName());

        repository.save(role);
    }

    public void delete(long id) {

        repository.delete(id);
    }

}
