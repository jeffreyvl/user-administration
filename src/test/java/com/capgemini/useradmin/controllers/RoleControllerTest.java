package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.Role;
import com.capgemini.useradmin.repository.RoleRepository;
import com.capgemini.useradmin.services.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Role> page;

    @InjectMocks
    private RoleController roleController;

    @Test
    public void getAllRoles() {
        Role role = new Role();
        role.setName("Pipo");
        role.setUsers(Collections.emptyList());
        role.setId(1);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        when(roleService.getAll()).thenReturn(roleList);
        Iterable<Role> roleIterable = roleController.getAll();
        verify(roleService, times(1)).getAll();

        assertEquals(1, roleIterable.spliterator().getExactSizeIfKnown());

        Role ret = roleIterable.iterator().next();
        assertTrue(ret.equals(role));
    }
    @Test
    public void getAllRolesByPage() {
        Role role = new Role();
        role.setName("Pipo");
        role.setUsers(Collections.emptyList());
        role.setId(1);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        when(roleService.listAllByPage(pageable)).thenReturn(page);
        Page ret = roleController.listAllByPage(pageable);

        assertTrue(ret.equals(page));
    }



    @Test
    public void getRole() {
        final long id = 1;
        Role role = new Role();
        role.setName("Pipo");
        role.setUsers(Collections.emptyList());
        role.setId(id);
        when(roleService.get(id)).thenReturn(role);
        Role roleGetter = roleController.get(id);
        verify(roleService, times(1)).get(id);

        assertTrue(roleGetter.equals(role));
    }

    @Test
    public void getEmptyRole() {
        final long id = 4;
        when(roleService.get(id)).thenReturn(null);
        try {
            Role roleGetter = roleController.get(id);
            assertTrue(false);
        }
        catch (BadRequestException e){
            assertTrue(true);
        }
        catch (Exception e){
            assertTrue(false);
        }

    }

    @Test
    public void addRole() {
        Role role = new Role();
        role.setName("Pipo");
        role.setUsers(Collections.emptyList());
        role.setId(1);
        roleController.add(role);
        ArgumentCaptor<Role> argument = ArgumentCaptor.forClass(Role.class);
        verify(roleService, times(1)).add(argument.capture());
        assertTrue(role.equals(argument.getValue()));

    }

    @Test
    public void updateRole() {
        final long id = 1;
        Role role = new Role();
        role.setName("Pipo");
        role.setUsers(Collections.emptyList());
        role.setId(id);
        roleController.update(role, id);
        ArgumentCaptor<Role> argument = ArgumentCaptor.forClass(Role.class);
        ArgumentCaptor<Long> longArg = ArgumentCaptor.forClass(Long.class);
        verify(roleService, times(1)).update(argument.capture(), longArg.capture());
        assertTrue(role.equals(argument.getValue()));
        assertTrue(id == longArg.getValue());
    }

    @Test
    public void deleteRole() {
        final long id = 1;
        roleController.delete(id);
        ArgumentCaptor<Long> longArg = ArgumentCaptor.forClass(Long.class);
        verify(roleService, times(1)).delete(longArg.capture());
        assertTrue(id == longArg.getValue());
    }
}
