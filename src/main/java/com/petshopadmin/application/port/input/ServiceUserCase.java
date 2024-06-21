package com.petshopadmin.application.port.input;

import com.petshopadmin.adapter.output.repository.database.ServiceDatabase;
import com.petshopadmin.application.domain.ServiceDomain;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public interface ServiceUserCase {

    ServiceDomain getByID(Long contractID, Long ID)
            throws NotFoundException, InternalServerErrorException;

    List<ServiceDomain> getByActive(Long contractID, boolean active)
            throws NotFoundException, InternalServerErrorException;

    ServiceDomain create(ServiceDomain serviceDomain) throws NotFoundException, InternalServerErrorException, IllegalArgumentException;

}
