package com.capgemini.useradmin.userService;

import com.capgemini.useradmin.exceptions.BadRequestException;
import com.capgemini.useradmin.model.domain.Role;
import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.model.view.user.UserCreateViewModel;
import com.capgemini.useradmin.model.view.user.UserEditViewModel;
import com.capgemini.useradmin.model.view.user.UserViewModel;
import com.capgemini.useradmin.repository.RoleRepository;
import com.capgemini.useradmin.repository.UserRepository;
import com.capgemini.useradmin.services.UserService;
import com.capgemini.useradmin.util.UserViewModelMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.junit.Assert.*;

import javax.persistence.Convert;
import java.time.LocalDate;

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
    Page<User> page;

    @Mock
    Page<Object> pageUserViewModel;

    @Mock
    Pageable pageable;

    @InjectMocks
    private UserService userService;

    @Before
    public void init() {
        userService = new UserService(userRepository, roleRepository);
    }

    @Test
    public void testGetUser() {
        when(userRepository.findOne(1L)).thenReturn(user);
        when(user.getStartDate()).thenReturn(LocalDate.now());
        userService.get(1L);
        verify(userRepository, times(1)).findOne(1L);
    }

    @Test(expected = BadRequestException.class)
    public void testGetNullUser() {
        long id = 8L;
        userService.get(id);
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
        when(modelMapper.map(cvm, User.class)).thenReturn(tempUser);
        userService.add(cvm);
        verify(userRepository, times(1)).save(tempUser);
    }

    @Test(expected = BadRequestException.class)
    public void testNullAdd() {
        long id = 10;
        UserCreateViewModel cvm = new UserCreateViewModel();
        cvm.setRoleId(id);
        userService.add(cvm);
    }

    @Test
    public void testDelete() {
        final long id = 1L;
        User deleteUser = new User();
        when(userRepository.findOne(1L)).thenReturn(deleteUser);
        userService.delete(1L);
        verify(userRepository, times(1)).delete(id);
    }

    @Test
    public void testListAllByPage() {
        when(userRepository.findAll(pageable)).thenReturn(page);

        when(page.map(anyObject())).thenReturn(this.pageUserViewModel);

        Page<UserViewModel> result = userService.listAllByPage(pageable);
        verify(userRepository, times(1)).findAll(pageable);
        Assert.assertEquals(pageUserViewModel, result);
    }

    @Test
    public void testSave() {
        final long id = 1L;
        UserEditViewModel model = new UserEditViewModel();
        when(userRepository.findOne(id)).thenReturn(user);
        userService.save(model,id);
        verify(userRepository, times(1)).save(user);
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


