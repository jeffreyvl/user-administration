package com.capgemini.useradmin.controllers;

import com.capgemini.useradmin.model.domain.ScheduleEntry;
import com.capgemini.useradmin.model.view.schedule.ScheduleDayEditViewModel;
import com.capgemini.useradmin.model.view.schedule.ScheduleDayViewModel;
import com.capgemini.useradmin.model.view.schedule.ScheduleEditViewModel;
import com.capgemini.useradmin.model.view.schedule.ScheduleViewModel;
import com.capgemini.useradmin.services.ScheduleEntryService;
import com.capgemini.useradmin.util.HelperMethods;
import com.capgemini.useradmin.util.Shift;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleEntryControllerTest {

    @Mock
    private ScheduleEntryService scheduleEntryService;

    @Captor
    private ArgumentCaptor<ArrayList<ScheduleEditViewModel>> captor;

    @Captor
    private ArgumentCaptor<ArrayList<ScheduleDayEditViewModel>> daycaptor;

    @InjectMocks
    private ScheduleEntryController scheduleEntryController;


    @Test
    public void getWeek() {
        final int year = 2012;
        final int week = 12;
        List<ScheduleViewModel> modelList = new ArrayList<>();
        ScheduleViewModel model = new ScheduleViewModel(HelperMethods.getDateFirstDay(year, week));
        modelList.add(model);
        when(scheduleEntryService.getWeek(year,week)).thenReturn(modelList);
        List<ScheduleViewModel> modelGetter = scheduleEntryController.getWeek(year, week);
        verify(scheduleEntryService, times(1)).getWeek(year, week);
        assertTrue(modelGetter.equals(modelList));
    }

    @Test
    public void getDay() {
        List<ScheduleDayViewModel> modelList = new ArrayList<>();
        LocalDate date = LocalDate.of(2012,12,12);
        ScheduleDayViewModel model = new ScheduleDayViewModel(date);
        modelList.add(model);
        when(scheduleEntryService.getDay(date)).thenReturn(modelList);
        List<ScheduleDayViewModel> modelGetter = scheduleEntryController.getDay(date);
        verify(scheduleEntryService, times(1)).getDay(date);
        assertTrue(modelGetter.equals(modelList));
    }

    @Test
    public void generateWeek() {
        final int year = 2012;
        final int week = 12;
        scheduleEntryController.generateWeek(year, week);
        ArgumentCaptor<Integer> yearArg = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> weekArg = ArgumentCaptor.forClass(Integer.class);
        verify(scheduleEntryService, times(1)).generateWeek(yearArg.capture(), weekArg.capture());
        assertTrue(year == yearArg.getValue());
        assertTrue(week == weekArg.getValue());
    }

    @Test
    public void generateDay() {
        LocalDate date = LocalDate.of(2012,12,12);
        scheduleEntryController.generateDay(date);
        ArgumentCaptor<LocalDate> dateArg = ArgumentCaptor.forClass(LocalDate.class);
        verify(scheduleEntryService, times(1)).generate(dateArg.capture());
        assertTrue(date == dateArg.getValue());
    }

    @Test
    public void saveWeek() {
        List<ScheduleEditViewModel> modelList = new ArrayList<>();
        ScheduleEditViewModel model = new ScheduleEditViewModel();
        modelList.add(model);
        scheduleEntryController.saveWeek(modelList);
        verify(scheduleEntryService, times(1)).saveWeek(captor.capture());
        assertTrue(modelList == captor.getValue());

    }

    @Test
    public void saveDay() {
        List<ScheduleDayEditViewModel> modelList = new ArrayList<>();
        ScheduleDayEditViewModel model = new ScheduleDayEditViewModel();
        modelList.add(model);
        scheduleEntryController.saveDay(modelList);
        verify(scheduleEntryService, times(1)).saveDay(daycaptor.capture());
        assertTrue(modelList == daycaptor.getValue());
    }

}
