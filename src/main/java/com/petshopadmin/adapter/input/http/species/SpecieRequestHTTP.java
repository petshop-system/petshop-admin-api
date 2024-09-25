package com.petshopadmin.adapter.input.http.species;

import java.io.Serializable;

public record SpecieRequestHTTP (
                                    String name,
                                    Long id) implements Serializable {}
