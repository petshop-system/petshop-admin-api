package com.petshopadmin.application.port.input;

import com.petshopadmin.application.domain.ContractDomain;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;

public interface ContractUserCase {
    ContractDomain getById(Long id) throws NotFoundException, InternalServerErrorException;
}
