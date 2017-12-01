package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.view.schedule.DefaultEditViewModel;
import com.capgemini.useradmin.model.view.schedule.DefaultViewModel;
import com.capgemini.useradmin.model.view.user.UserViewModel;
import com.capgemini.useradmin.services.DefaultEntryService;
import com.capgemini.useradmin.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultEntryControllerTest {

    @Mock
    private DefaultEntryService defaultEntryService;

    @Mock
    private DefaultViewModel defaultViewModel;

    @InjectMocks
    private DefaultEntryController defaultEntryController;


    @Test
    public void getDefaultEntry() {
        final long id = 1;
        Assert.assertNotNull(this.defaultEntryService);
        when(defaultEntryService.get(id)).thenReturn(defaultViewModel);
        DefaultViewModel defaultGetter = defaultEntryController.get(id);
        verify(defaultEntryService, times(1)).get(id);

        assertTrue(defaultGetter.equals(defaultViewModel));
    }

    @Test
    public void updateUser() {
        DefaultEditViewModel dEVM = new DefaultEditViewModel();
        defaultEntryController.save(dEVM);
        ArgumentCaptor<DefaultEditViewModel> argument = ArgumentCaptor.forClass(DefaultEditViewModel.class);
        verify(defaultEntryService, times(1)).save(argument.capture());
        assertTrue(dEVM.equals(argument.getValue()));
    }
}