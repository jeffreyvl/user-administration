package com.capgemini.useradmin.model.view.schedule;

import com.capgemini.useradmin.model.domain.ScheduleEntry;
import com.capgemini.useradmin.util.Shift;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Map;

public class ScheduleViewModel {

    private long userId;
    private String firstName;
    private String lastName;

    private Map<LocalDate, Map<Shift, Boolean>> scheduleEntries;

    public ScheduleViewModel(int year, int week) {

        scheduleEntries = new HashMap<>();

        LocalDate date = LocalDate.of(year, 7, 1);
        date = date.with(WeekFields.ISO.weekOfWeekBasedYear(), week);
        date = date.with(WeekFields.ISO.dayOfWeek(), 1);

        for (int i = 0; i < 7; i++) {

            scheduleEntries.put(date.plusDays(i), new HashMap<>());

            for (Shift shift : Shift.values()) {

                scheduleEntries.get(date.plusDays(i)).put(shift, false);
            }
        }
    }

    public void add(ScheduleEntry scheduleEntry) {

        scheduleEntries.get(scheduleEntry.getDate()).put(scheduleEntry.getShift(), true);
    }

    public Map<LocalDate, Map<Shift, Boolean>> getScheduleEntries() {
        return scheduleEntries;
    }

    public void setScheduleEntries(Map<LocalDate, Map<Shift, Boolean>> scheduleEntries) {
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
}
