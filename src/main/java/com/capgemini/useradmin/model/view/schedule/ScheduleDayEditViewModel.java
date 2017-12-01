package com.capgemini.useradmin.model.view.schedule;

import com.capgemini.useradmin.util.Shift;

import java.time.LocalDate;
import java.util.Map;

public class ScheduleDayEditViewModel {

    private long userId;
    private LocalDate date;
    private Map<Shift, Boolean> scheduleEntries;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<Shift, Boolean> getScheduleEntries() {
        return scheduleEntries;
    }

    public void setScheduleEntries(Map<Shift, Boolean> scheduleEntries) {
        this.scheduleEntries = scheduleEntries;
    }
}

