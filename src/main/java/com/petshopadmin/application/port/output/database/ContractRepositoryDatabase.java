package com.petshopadmin.application.port.output.database;

import com.petshopadmin.application.domain.ContractDomain;

public interface ContractRepositoryDatabase {
    ContractDomain getById(Long id);
}
