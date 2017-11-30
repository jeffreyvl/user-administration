package com.capgemini.useradmin.model.view.schedule;

import com.capgemini.useradmin.model.domain.DefaultEntry;
import com.capgemini.useradmin.util.Shift;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class DefaultViewModel {

    private Map<DayOfWeek, Map<Shift,Boolean>> defaultEntries;

    public DefaultViewModel() {

        defaultEntries =  new HashMap<>();
        for (DayOfWeek day: DayOfWeek.values()) {

            defaultEntries.put(day, new HashMap<>());

            for (Shift shift: Shift.values()) {

                defaultEntries.get(day).put(shift,false);
            }
        }
    }

    public void add(DefaultEntry defaultEntry) {

        defaultEntries.get(defaultEntry.getDay()).put(defaultEntry.getShift(), true);
    }
    public Map<DayOfWeek, Map<Shift, Boolean>> getDefaultEntries() {
        return defaultEntries;
    }

    public void setDefaultEntries(Map<DayOfWeek, Map<Shift, Boolean>> defaultEntries) {
        this.defaultEntries = defaultEntries;
    }
}
