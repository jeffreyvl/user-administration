package com.capgemini.useradmin.model.view.schedule;


import com.capgemini.useradmin.util.Shift;
import java.time.LocalDate;
import java.util.Map;

public class ScheduleEditViewModel {

    private long userId;

    private Map<LocalDate, Map<Shift, Boolean>> scheduleEntries;

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

}
