package com.capgemini.useradmin.services;

import com.capgemini.useradmin.model.Role;
import com.capgemini.useradmin.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class RoleService extends Service<Role> {

    @Autowired
    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {

        repository = roleRepository;

    }
}
