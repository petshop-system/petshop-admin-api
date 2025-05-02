package com.petshopadmin.adapter.input.http.species;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.petshopadmin.application.domain.SpecieDomain;
import lombok.Data;

@Data
public class SpecieResponseHTTP {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("disabled")
    private boolean disabled;

    SpecieResponseHTTP (SpecieDomain specieDomain) {
        this.setId(specieDomain.getId());
        this.setName(specieDomain.getName());
        this.setDisabled(specieDomain.isDisabled());
    }
}
