package com.petshopadmin.adapter.input.http;

import com.petshopadmin.adapter.output.repository.database.ContractDatabase;

import java.io.Serializable;
import java.math.BigDecimal;

public record ServiceRequestHTTP(String name, BigDecimal price,
                                 boolean active, String description) implements Serializable { }
