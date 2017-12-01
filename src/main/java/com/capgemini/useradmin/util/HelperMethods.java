package com.capgemini.useradmin.util;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

public class HelperMethods {

    public static LocalDate getDateFirstDay(int year, int week) {

        LocalDate date = LocalDate.of(year, 7, 1);
        date = date.with(WeekFields.ISO.weekOfWeekBasedYear(), week);
        date = date.with(WeekFields.ISO.dayOfWeek(), 1);
        return date;
    }
}
