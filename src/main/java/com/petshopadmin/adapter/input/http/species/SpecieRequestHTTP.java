package com.petshopadmin.adapter.input.http.species;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record SpecieRequestHTTP (
                                    String name,
                                    @JsonProperty("specie_id") Long specieid) implements Serializable {}
