package com.petshopadmin.application.port.input;

import com.petshopadmin.application.domain.ServiceDomain;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;

public interface ServiceValidation {

    void validateServiceDomain (ServiceDomain serviceDomain) throws InternalServerErrorException, IllegalArgumentException, NotFoundException;
}
