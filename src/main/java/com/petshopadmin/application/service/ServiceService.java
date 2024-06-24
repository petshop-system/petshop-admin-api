package com.petshopadmin.application.service;

import com.petshopadmin.adapter.output.repository.database.ServiceDatabase;
import com.petshopadmin.application.domain.ServiceDomain;
import com.petshopadmin.application.port.input.ServiceUserCase;
import com.petshopadmin.application.port.output.database.ServiceRepositoryDatabase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServiceService implements ServiceUserCase {

    static String SERVICE_NOT_FOUND = "service not found";
    static String SERVICE_INTERNAL_SERVER_ERROR = "service internal error";
    static String ILLEGAL_ARGUMENT_NAME_EXCEPTION = "name cannot be null or empty";
    static String ILLEGAL_ARGUMENT_CHARACTERS_MAX_NAME_EXCEPTION = "exceeds maximum length of 255 characters";
    static String ILLEGAL_ARGUMENT_PRICE_EXCEPTION = "service price cannot be null or negative";
    static String ILLEGAL_ARGUMENT_DESCRIPTION_EXCEPTION = "description cannot be null or empty";
    static String ILLEGAL_ARGUMENT_CHARACTERS_MAX_DESCRIPTION_EXCEPTION = "description exceeds maximum length of 255 characters";
    static String ILLEGAL_ARGUMENT_CONTRACT_EXCEPTION = "Contract ID cannot be null or empty";

    private final ServiceRepositoryDatabase serviceRepositoryDatabase;

    public ServiceService (ServiceRepositoryDatabase serviceRepositoryDatabase) {
        this.serviceRepositoryDatabase = serviceRepositoryDatabase;
    }

    @Override
    public ServiceDomain getByID(Long contractID, Long ID) throws NotFoundException, InternalServerErrorException {

        if (ObjectUtils.isEmpty(contractID) || ObjectUtils.isEmpty(ID)) {
            throw new InternalServerErrorException(ServiceService.SERVICE_INTERNAL_SERVER_ERROR);
        }

        ServiceDomain serviceDomain = serviceRepositoryDatabase.getByID(contractID, ID);
        if (ObjectUtils.isEmpty(serviceDomain)) {
            throw new NotFoundException(ServiceService.SERVICE_NOT_FOUND);
        }

        return serviceDomain;
    }

    @Override
    public List<ServiceDomain> getByActive(Long contractID, boolean active) throws InternalServerErrorException {

        if (ObjectUtils.isEmpty(contractID)) {
            throw new InternalServerErrorException(ServiceService.SERVICE_INTERNAL_SERVER_ERROR);
        }

        List<ServiceDomain> list = serviceRepositoryDatabase.getByActive(contractID, active);
        if (Objects.isNull(list)) {
            list = new ArrayList<>();
        }

        return list;
    }

    @Override
    @Transactional
    public ServiceDomain create(ServiceDomain serviceDomain) throws NotFoundException,InternalServerErrorException {
        validateServiceDomain(serviceDomain);

        return serviceRepositoryDatabase.save(serviceDomain);
    }

    private void validateServiceDomain(ServiceDomain serviceDomain) throws InternalServerErrorException, IllegalArgumentException, NotFoundException {
        if (ObjectUtils.isEmpty(serviceDomain)) {
            throw new InternalServerErrorException(SERVICE_INTERNAL_SERVER_ERROR);
        }
        if (ObjectUtils.isEmpty(serviceDomain.getName().trim())) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_NAME_EXCEPTION);
        }
        if (serviceDomain.getName().length() > 255) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_CHARACTERS_MAX_NAME_EXCEPTION);
        }
        if (ObjectUtils.isEmpty(serviceDomain.getPrice()) || serviceDomain.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_PRICE_EXCEPTION);
        }
        if (!serviceDomain.isActive()) {
            throw new NotFoundException(SERVICE_NOT_FOUND);
        }
        if (ObjectUtils.isEmpty(serviceDomain.getDescription().trim())) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_DESCRIPTION_EXCEPTION);
        }
        if (serviceDomain.getDescription().length() > 255) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_CHARACTERS_MAX_DESCRIPTION_EXCEPTION);
        }
        if (ObjectUtils.isEmpty(serviceDomain.getContract()) || ObjectUtils.isEmpty(serviceDomain.getContract().getId())) {
            throw new InternalServerErrorException(ILLEGAL_ARGUMENT_CONTRACT_EXCEPTION);
        }
    }

}
