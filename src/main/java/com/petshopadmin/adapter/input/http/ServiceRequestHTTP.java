package com.petshopadmin.adapter.input.http;

import java.io.Serializable;
import java.math.BigDecimal;

public record ServiceRequestHTTP(String name, BigDecimal price,
                                 boolean active, String description, Long contractId) implements Serializable { }
