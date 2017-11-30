package com.capgemini.useradmin.model.view.user;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class UserCreateViewModel {

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid first name.")
    private String firstName;
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid first name.")
    private String lastName;
    @Email(message = "Invalid email address")
    private String email;
    @NotNull
    private LocalDate startDate;
    private long roleId;

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

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}


