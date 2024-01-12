package com.petshopadmin.application.service;

import com.petshopadmin.application.domain.ServiceDomain;
import com.petshopadmin.application.port.input.ServiceUserCase;
import com.petshopadmin.application.port.output.database.ServiceRepositoryDatabase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServiceService implements ServiceUserCase {

    static String SERVICE_NOT_FOUND = "service not found";
    static String SERVICE_INTERNAL_SERVER_ERROR = "service internal error";

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

        List<ServiceDomain> list = serviceRepositoryDatabase.getByActive(contractID, active);
        if (Objects.isNull(list)) {
            list = new ArrayList<>();
        }

        return list;
    }
}
