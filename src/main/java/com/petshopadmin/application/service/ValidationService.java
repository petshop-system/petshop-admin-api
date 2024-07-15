package com.petshopadmin.application.service;

import com.petshopadmin.application.domain.ServiceDomain;
import com.petshopadmin.exception.InternalServerErrorException;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ValidationService  {

    static String SERVICE_NOT_FOUND = "service not found";
    static String SERVICE_INTERNAL_SERVER_ERROR = "service internal error";
    static String ILLEGAL_ARGUMENT_NAME_EXCEPTION = "name cannot be null or empty";
    static String ILLEGAL_ARGUMENT_CHARACTERS_MAX_NAME_EXCEPTION = "exceeds maximum length of 255 characters";
    static String ILLEGAL_ARGUMENT_PRICE_EXCEPTION = "service price cannot be null or negative";
    static String ILLEGAL_ARGUMENT_DESCRIPTION_EXCEPTION = "description cannot be null or empty";
    static String ILLEGAL_ARGUMENT_CHARACTERS_MAX_DESCRIPTION_EXCEPTION = "description exceeds maximum length of 255 characters";
    static String ILLEGAL_ARGUMENT_CONTRACT_EXCEPTION = "Contract ID cannot be null or empty";

    public void validate(ServiceDomain serviceDomain) throws InternalServerErrorException, IllegalArgumentException {
        List<String> errors = new ArrayList<>();

        if (ObjectUtils.isEmpty(serviceDomain)) {
            throw new InternalServerErrorException(SERVICE_INTERNAL_SERVER_ERROR);
        }
        if (ObjectUtils.allNotNull(serviceDomain, serviceDomain.getName()) &&
                !serviceDomain.getName().trim().isEmpty()) {
            serviceDomain.setName(serviceDomain.getName().trim());
        }
        else {
            errors.add(ILLEGAL_ARGUMENT_NAME_EXCEPTION);
        }
        if (ObjectUtils.allNotNull(serviceDomain, serviceDomain.getName()) &&
                serviceDomain.getName().length() < 255) {
        } else {
            errors.add(ILLEGAL_ARGUMENT_CHARACTERS_MAX_NAME_EXCEPTION);
        }
        if (ObjectUtils.allNotNull(serviceDomain, serviceDomain.getPrice()) && serviceDomain.getPrice().compareTo(BigDecimal.ZERO) > 0) {
        } else {
            errors.add(ILLEGAL_ARGUMENT_PRICE_EXCEPTION);
        }
        if (ObjectUtils.isNotEmpty(serviceDomain) && serviceDomain.isActive()) {
        } else {
            errors.add(SERVICE_NOT_FOUND);
        }
        if (serviceDomain != null && serviceDomain.getDescription() != null && !serviceDomain.getDescription().trim().isEmpty()) {
            serviceDomain.setDescription(serviceDomain.getDescription().trim());
        }
        else {
            errors.add(ILLEGAL_ARGUMENT_DESCRIPTION_EXCEPTION);
        }
        if (ObjectUtils.allNotNull(serviceDomain, serviceDomain.getDescription()) &&
                serviceDomain.getDescription().length() < 255) {
        } else {
            errors.add(ILLEGAL_ARGUMENT_CHARACTERS_MAX_DESCRIPTION_EXCEPTION);
        }
        if (ObjectUtils.isNotEmpty(serviceDomain.getContract().getId())) {
        } else {
            errors.add(ILLEGAL_ARGUMENT_CONTRACT_EXCEPTION);
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }
}