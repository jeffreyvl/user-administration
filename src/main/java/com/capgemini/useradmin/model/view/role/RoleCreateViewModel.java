package com.capgemini.useradmin.model.view.role;

import javax.validation.constraints.Pattern;

public class RoleCreateViewModel {

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid name.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
