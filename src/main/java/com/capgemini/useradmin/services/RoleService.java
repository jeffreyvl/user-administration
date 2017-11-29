package com.capgemini.useradmin.services;

import com.capgemini.useradmin.model.Role;
import com.capgemini.useradmin.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {

        repository = roleRepository;

    }
}
