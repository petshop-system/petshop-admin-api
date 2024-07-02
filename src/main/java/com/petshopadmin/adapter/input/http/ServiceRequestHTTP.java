package com.petshopadmin.adapter.input.http;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public record ServiceRequestHTTP(String name,
                                 BigDecimal price,
                                 boolean active,
                                 String description,
                                 @JsonProperty("contract_id") Long contractid) implements Serializable { }
