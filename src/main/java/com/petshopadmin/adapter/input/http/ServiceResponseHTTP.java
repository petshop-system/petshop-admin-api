package com.petshopadmin.adapter.input.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.petshopadmin.application.domain.ServiceDomain;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Data
public class ServiceResponseHTTP {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("description")
    private String description;

    ServiceResponseHTTP (ServiceDomain serviceDomain) {
        this.setId(serviceDomain.getId());
        this.setName(serviceDomain.getName());
        this.setPrice(serviceDomain.getPrice());
        this.setActive(serviceDomain.isActive());
        this.setDescription(serviceDomain.getDescription());
    }

}
