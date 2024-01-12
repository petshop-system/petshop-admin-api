package com.petshopadmin.application.domain;

import java.math.BigDecimal;

public class ServiceDomain {

    private Long id;

    private String name;

    private BigDecimal price;

    private boolean active;

    private String description;

    private ContractDomain contract;

    public ServiceDomain() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ContractDomain getContract() {
        return contract;
    }

    public void setContract(ContractDomain contract) {
        this.contract = contract;
    }
}
