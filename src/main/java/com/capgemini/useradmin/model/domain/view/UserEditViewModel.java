package com.capgemini.useradmin.model.domain.view;

import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.Pattern;


public class UserEditViewModel {

    private long id;
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid first name.")
    private String firstName;
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid first name.")
    private String lastName;
    @Email(message = "Invalid email address")
    private String email;

    private long roleId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}


