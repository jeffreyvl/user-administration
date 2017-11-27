package com.capgemini.useradmin.model;

import com.capgemini.useradmin.util.Shift;
import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
public class DefaultEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private DayOfWeek day;
    private Shift shift;

    @ManyToOne
    private User user;

    public DefaultEntry() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
