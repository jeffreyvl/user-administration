package com.capgemini.useradmin.model.view.schedule;

import com.capgemini.useradmin.model.domain.ScheduleEntry;
import com.capgemini.useradmin.util.Shift;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ScheduleDayViewModel {

    private long userId;
    private String firstName;
    private String lastName;
    private LocalDate date;

    private Map<Shift, Boolean> scheduleEntries;

    public ScheduleDayViewModel(LocalDate date) {

        this.date = date;
        scheduleEntries = new HashMap<>();

        for (Shift shift : Shift.values()) {

            scheduleEntries.put(shift, false);
        }
    }

    public void add(ScheduleEntry scheduleEntry) {

        scheduleEntries.put(scheduleEntry.getShift(), true);
    }

    public Map<Shift, Boolean> getScheduleEntries() {
        return scheduleEntries;
    }

    public void setScheduleEntries(Map<Shift, Boolean> scheduleEntries) {
        this.scheduleEntries = scheduleEntries;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
