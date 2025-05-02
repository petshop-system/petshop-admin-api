package com.petshopadmin.application.domain;

public class SpecieDomain {
    private Long id;

    private String name;

    private boolean disabled;

    public void setId(Long id) { this.id = id; }

    public Long getId() {return id; }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public boolean isDisabled() { return disabled; }

    public void setDisabled(boolean disabled) { this.disabled = disabled; }
}
