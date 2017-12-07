package com.capgemini.useradmin.userService;

import com.capgemini.useradmin.controllers.UserController;
import com.capgemini.useradmin.model.domain.Role;
import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.model.view.user.UserCreateViewModel;
import com.capgemini.useradmin.model.view.user.UserEditViewModel;
import com.capgemini.useradmin.model.view.user.UserViewModel;
import com.capgemini.useradmin.repository.RoleRepository;
import com.capgemini.useradmin.repository.UserRepository;
import com.capgemini.useradmin.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    User user;

    @Mock
    ModelMapper modelMapper;

    @Mock
    Role role;

    @Mock
    Pageable pageable;

    @InjectMocks
    private UserService userService;

    @Before
    public void init() {
        userService = new UserService(userRepository, roleRepository );
    }

    @Test
    public void testGetUser() {
        when(userRepository.findOne(1L)).thenReturn(user);
        when(user.getStartDate()).thenReturn(LocalDate.now());
        userService.get(1L);
        verify(userRepository, times(1)).findOne(1L);
    }

    @Test(expected = Exception.class)
    public void testFindUserNotFound() {
        when(userRepository.findOne(1L)).thenThrow(new Exception());
        userService.get(1);
    }

    @Test
    public void testAdd() {
        User tempUser = new User();
        UserCreateViewModel cvm = new UserCreateViewModel();
        cvm.setRoleId(1L);
        Role role = new Role();
        when(roleRepository.findOne(1L)).thenReturn(role);
        when(modelMapper.map(cvm,User.class)).thenReturn(tempUser);
        userService.add(cvm);
        verify(userRepository, times(1)).save(tempUser);
    }

    @Test
    public void testDelete() {
        final long id = 1L;
        User deleteUser = new User();
        when(userRepository.findOne(1L)).thenReturn(deleteUser);
        userService.delete(1L);
        verify(userRepository, times(1)).delete(id);
    }

    //    @Test
//    public void testFindAll() {
//        UserViewModel uvm = new UserViewModel();
//        when(userRepository.findAll()).thenReturn(new ArrayList<>());
//        Iterable<User> result = userService.listAllByPage();
//        Assert.assertEquals(0, result.spliterator().getExactSizeIfKnown());
//    }

}

//    @Test
//    public void testAddUserService() {
//        User findUser = new User();
//        UserCreateViewModel cvm = new UserCreateViewModel();
//        cvm.setRoleId(1L);
//        Role role = new Role();
//        when(roleRepository.findOne(1L)).thenReturn(role);
//        when(modelMapper.map(cvm,User.class)).thenReturn(findUser);
//        userService.add(cvm);
//        verify(userRepository, times(1)).save(findUser);
//    }


