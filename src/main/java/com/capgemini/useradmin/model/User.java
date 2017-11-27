package com.capgemini.useradmin.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Pattern(regexp="/^[a-zA-Z]{2,20}$/",  message = "Invalid Name")
    private String name;
    @Email(message = "Invalid email address")
    private String email;
    @OneToMany
    private List<DefaultEntry> defaultEntries;
    @OneToMany
    private List<ScheduleEntry> scheduleEntries;
    @NotNull
    @OneToOne
    private Role role;

    public User() {
    }

    public List<ScheduleEntry> getScheduleEntries() {
        return scheduleEntries;
    }

    public void setScheduleEntries(List<ScheduleEntry> scheduleEntries) {
        this.scheduleEntries = scheduleEntries;
    }

    public List<DefaultEntry> getDefaultEntries() {
        return defaultEntries;
    }

    public void setDefaultEntries(List<DefaultEntry> defaultEntries) {
        this.defaultEntries = defaultEntries;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
