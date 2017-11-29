package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.Role;
import com.capgemini.useradmin.repository.RoleRepository;
import com.capgemini.useradmin.services.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @Mock
    private Role role;

    @InjectMocks
    private RoleController roleController;

    @Test
    public void getAllRoles() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        when(roleService.getAll()).thenReturn(roleList);
        Iterable<Role> roleIterable = roleController.getAll();
        verify(roleService, times(1)).getAll();
        assertEquals(1, roleIterable.spliterator().getExactSizeIfKnown());
    }
}
