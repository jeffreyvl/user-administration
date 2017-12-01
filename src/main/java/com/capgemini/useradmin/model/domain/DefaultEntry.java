package com.capgemini.useradmin.model.domain;

import com.capgemini.useradmin.util.Shift;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity(name = "DefaultEntry")
@Table(name = "default_entry")
public class DefaultEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "day")
    private DayOfWeek day;
    @Column(name = "shift")
    private Shift shift;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
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
