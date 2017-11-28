package com.capgemini.useradmin.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Pattern(regexp="/^[a-zA-Z]{2,20}$/",  message = "Invalid First Name")
    private String firstName;
    @Pattern(regexp="/^[a-zA-Z]{2,20}$/",  message = "Invalid Last Name")
    private String lastName;
    @Email(message = "Invalid email address")
    private String email;
    @NotNull
    private LocalDate startDate;
    @OneToMany
    private List<DefaultEntry> defaultEntries;
    @OneToMany
    private List<ScheduleEntry> scheduleEntries;
    @NotNull
    @ManyToOne
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
