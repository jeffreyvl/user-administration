package com.capgemini.useradmin.model.view.schedule;

import com.capgemini.useradmin.util.Shift;

import java.time.DayOfWeek;
import java.util.Map;

public class DefaultEditViewModel {

    private long userId;
    private Map<DayOfWeek, Map<Shift,Boolean>> defaultEntries;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Map<DayOfWeek, Map<Shift, Boolean>> getDefaultEntries() {
        return defaultEntries;
    }

    public void setDefaultEntries(Map<DayOfWeek, Map<Shift, Boolean>> defaultEntries) {
        this.defaultEntries = defaultEntries;
    }
}
