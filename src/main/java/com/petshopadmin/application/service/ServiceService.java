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
    static String ILLEGAL_ARGUMENT_PRICE_EXCEPTION = "service price cannot be null or negative";
    static String ILLEGAL_ARGUMENT_DESCRIPTION_EXCEPTION = "Description cannot be null or empty";
    static String ILLEGAL_ARGUMENT_CHARACTERS_MAX_DESCRIPTION_EXCEPTION = "Description exceeds maximum length of 255 characters";


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
        if (ObjectUtils.isEmpty(serviceDomain)) {
            throw new InternalServerErrorException(SERVICE_INTERNAL_SERVER_ERROR);
        }
        if (Objects.isNull(serviceDomain.getName()) || serviceDomain.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_NAME_EXCEPTION);
        }
        if (serviceDomain.getPrice() == null || ObjectUtils.isEmpty(serviceDomain.getPrice()) || serviceDomain.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_PRICE_EXCEPTION);
        }
        if (!serviceDomain.isActive() ) {
            throw  new NotFoundException(SERVICE_NOT_FOUND);
        }
        if (Objects.isNull(serviceDomain.getDescription()) || serviceDomain.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_DESCRIPTION_EXCEPTION);
        }
        if (serviceDomain.getDescription().length() > 255) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_CHARACTERS_MAX_DESCRIPTION_EXCEPTION);
        }
        if (Objects.isNull(serviceDomain.getContract().getId())) {
            throw new InternalServerErrorException("Contract ID cannot be null");
        }





        return serviceRepositoryDatabase.save(serviceDomain);
    }

}
