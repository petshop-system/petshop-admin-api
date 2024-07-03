package com.petshopadmin.application.service;

import com.petshopadmin.application.domain.ServiceDomain;
import com.petshopadmin.application.port.input.ServiceValidation;
import com.petshopadmin.application.port.input.ServiceValidation;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ValidationService implements ServiceValidation {

    static String SERVICE_NOT_FOUND = "service not found";
    static String SERVICE_INTERNAL_SERVER_ERROR = "service internal error";
    static String ILLEGAL_ARGUMENT_NAME_EXCEPTION = "name cannot be null or empty";
    static String ILLEGAL_ARGUMENT_CHARACTERS_MAX_NAME_EXCEPTION = "exceeds maximum length of 255 characters";
    static String ILLEGAL_ARGUMENT_PRICE_EXCEPTION = "service price cannot be null or negative";
    static String ILLEGAL_ARGUMENT_DESCRIPTION_EXCEPTION = "description cannot be null or empty";
    static String ILLEGAL_ARGUMENT_CHARACTERS_MAX_DESCRIPTION_EXCEPTION = "description exceeds maximum length of 255 characters";
    static String ILLEGAL_ARGUMENT_CONTRACT_EXCEPTION = "Contract ID cannot be null or empty";

    @Override
    public void validateServiceDomain(ServiceDomain serviceDomain) throws InternalServerErrorException, IllegalArgumentException, NotFoundException {
        List<String> errors = new ArrayList<>();

        if (ObjectUtils.isEmpty(serviceDomain)) {
            throw new InternalServerErrorException(SERVICE_INTERNAL_SERVER_ERROR);
        }
        if (ObjectUtils.isEmpty(serviceDomain.getName().trim())) {
            errors.add(ILLEGAL_ARGUMENT_NAME_EXCEPTION);
        }
        if (serviceDomain.getName().length() > 255) {
            errors.add(ILLEGAL_ARGUMENT_CHARACTERS_MAX_NAME_EXCEPTION);
        }
        if (ObjectUtils.isEmpty(serviceDomain.getPrice()) || serviceDomain.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            errors.add(ILLEGAL_ARGUMENT_PRICE_EXCEPTION);
        }
        if (!serviceDomain.isActive()) {
            errors.add(SERVICE_NOT_FOUND);
        }
        if (ObjectUtils.isEmpty(serviceDomain.getDescription().trim())) {
            errors.add(ILLEGAL_ARGUMENT_DESCRIPTION_EXCEPTION);
        }
        if (serviceDomain.getDescription().length() > 255) {
            errors.add(ILLEGAL_ARGUMENT_CHARACTERS_MAX_DESCRIPTION_EXCEPTION);
        }
        if (ObjectUtils.isEmpty(serviceDomain.getContract().getId())) {
            errors.add(ILLEGAL_ARGUMENT_CONTRACT_EXCEPTION);
        }
        if (ObjectUtils.isEmpty(serviceDomain.getContract())) {
            errors.add("contrato inexistente");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }
}