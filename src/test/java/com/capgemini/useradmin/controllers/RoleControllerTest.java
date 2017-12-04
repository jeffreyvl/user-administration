package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.domain.Role;
import com.capgemini.useradmin.model.view.role.RoleCreateViewModel;
import com.capgemini.useradmin.model.view.role.RoleEditViewModel;
import com.capgemini.useradmin.model.view.role.RoleViewModel;
import com.capgemini.useradmin.services.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<RoleViewModel> page;

    @InjectMocks
    private RoleController roleController;

    @Test
    public void getAllRolesByPage() {
        when(roleService.listAllByPage(pageable)).thenReturn(page);
        Page ret = roleController.listAllByPage(pageable);

        assertTrue(ret.equals(page));
    }

    @Test
    public void getRole() {
        final long id = 1;
        RoleViewModel role = new RoleViewModel();
        role.setName("Pipo");
        role.setId(id);
        when(roleService.get(id)).thenReturn(role);
        RoleViewModel roleGetter = roleController.get(id);
        verify(roleService, times(1)).get(id);

        assertTrue(roleGetter.equals(role));
    }

    @Test
    public void addRole() {
        RoleCreateViewModel role = new RoleCreateViewModel();
        role.setName("Pipo");
        roleController.add(role);
        ArgumentCaptor<RoleCreateViewModel> argument = ArgumentCaptor.forClass(RoleCreateViewModel.class);
        verify(roleService, times(1)).add(argument.capture());
        assertTrue(role.equals(argument.getValue()));

    }

    @Test
    public void updateRole() {
        final long id = 1;
        RoleEditViewModel role = new RoleEditViewModel();
        role.setName("Pipo");
        role.setId(id);
        roleController.save(role, id);
        ArgumentCaptor<RoleEditViewModel> argument = ArgumentCaptor.forClass(RoleEditViewModel.class);
        ArgumentCaptor<Long> longArg = ArgumentCaptor.forClass(Long.class);
        verify(roleService, times(1)).save(argument.capture(), longArg.capture());
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

    @Test
    public void searchRole() {
        RoleViewModel role = new RoleViewModel();
        roleController.search(role);
        ArgumentCaptor<RoleViewModel> argument = ArgumentCaptor.forClass(RoleViewModel.class);
        verify(roleService, times(1)).search(argument.capture());
        assertTrue(role == argument.getValue());
    }
}
/**
 @RequestMapping(value = "/search/", method = RequestMethod.POST)
 public List<RoleViewModel> search(@Valid @RequestBody RoleViewModel view) {
 return service.search(view);
 */