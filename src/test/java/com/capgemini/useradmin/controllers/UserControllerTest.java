package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.domain.Role;
import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.model.view.role.RoleCreateViewModel;
import com.capgemini.useradmin.model.view.role.RoleEditViewModel;
import com.capgemini.useradmin.model.view.role.RoleViewModel;
import com.capgemini.useradmin.model.view.user.UserCreateViewModel;
import com.capgemini.useradmin.model.view.user.UserEditViewModel;
import com.capgemini.useradmin.model.view.user.UserViewModel;
import com.capgemini.useradmin.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserViewModel userViewModel;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<UserViewModel> page;

    @InjectMocks
    private UserController userController;



    @Test
    public void getAllUsersByPage() {
        when(userService.listAllByPage(pageable)).thenReturn(page);
        Page ret = userController.listAllByPage(pageable);

        assertTrue(ret.equals(page));
    }



    @Test
    public void getUser() {
        final long id = 3;
        when(userService.get(id)).thenReturn(userViewModel);
        UserViewModel userGetter = userController.get(id);
        verify(userService, times(1)).get(id);

        assertTrue(userGetter.equals(userViewModel));
    }

    @Test
    public void addUser() {
        UserCreateViewModel uCVM = new UserCreateViewModel();
        userController.add(uCVM);
        ArgumentCaptor<UserCreateViewModel> argument = ArgumentCaptor.forClass(UserCreateViewModel.class);
        verify(userService, times(1)).add(argument.capture());
        assertTrue(uCVM.equals(argument.getValue()));

    }

    @Test
    public void updateUser() {
        final long id = 1;
        UserEditViewModel uEVM = new UserEditViewModel();
        userController.save(uEVM, id);
        ArgumentCaptor<UserEditViewModel> argument = ArgumentCaptor.forClass(UserEditViewModel.class);
        ArgumentCaptor<Long> longArg = ArgumentCaptor.forClass(Long.class);
        verify(userService, times(1)).save(argument.capture(), longArg.capture());
        assertTrue(uEVM.equals(argument.getValue()));
        assertTrue(id == longArg.getValue());
    }

    @Test
    public void deleteRole() {
        final long id = 1;
        userController.delete(id);
        ArgumentCaptor<Long> longArg = ArgumentCaptor.forClass(Long.class);
        verify(userService, times(1)).delete(longArg.capture());
        assertTrue(id == longArg.getValue());
    }
}
