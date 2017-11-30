package com.capgemini.useradmin.model.domain.view;

import javax.validation.constraints.Pattern;

public class RoleEditViewModel {

    private long id;
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid name.")
    private String name;

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
}
