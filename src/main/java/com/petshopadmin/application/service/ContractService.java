package com.petshopadmin.application.service;

import com.petshopadmin.application.domain.ContractDomain;
import com.petshopadmin.application.port.input.ContractUserCase;
import com.petshopadmin.application.port.output.database.ContractRepositoryDatabase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Objects;

public class ContractService implements ContractUserCase {

    static String CONTRACT_NOT_FOUND = "Contract not found";
    static String CONTRACT_INTERNAL_SERVER_ERROR = "contract internal error";

    private final ContractRepositoryDatabase contractRepositoryDatabase;

    public ContractService(ContractRepositoryDatabase contractRepositoryDatabase) {
        this.contractRepositoryDatabase = contractRepositoryDatabase;
    }

    @Override
    public ContractDomain getById(Long id) throws NotFoundException, InternalServerErrorException {

        if (Objects.isNull(id)) {
            throw new InternalServerErrorException(CONTRACT_INTERNAL_SERVER_ERROR);
        }

        ContractDomain contract = contractRepositoryDatabase.getById(id);

        if (ObjectUtils.isEmpty(contract)) {
            throw  new NotFoundException(CONTRACT_NOT_FOUND);
        }

        return contract;
    }
}
