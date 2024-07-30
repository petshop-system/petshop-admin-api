package com.petshopadmin.adapter.output.repository.database;

import com.petshopadmin.application.domain.ContractDomain;
import org.apache.commons.lang3.ObjectUtils;

public class ContractRepositoryDatabase implements com.petshopadmin.application.port.output.database.ContractRepositoryDatabase {
    public final ContractJPARepository contractJPARepository;

    public ContractRepositoryDatabase(ContractJPARepository contractJPARepository) {
        this.contractJPARepository = contractJPARepository;
    }

    @Override
    public ContractDomain getById(Long id) {
        ContractDatabase contract = contractJPARepository.getByID(id);

        if (ObjectUtils.isEmpty(contract)) {
            return null;
        }

        return contract.toContractDomain();
    }
}