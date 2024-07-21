package com.petshopadmin.adapter.output.repository.database;

import com.petshopadmin.application.domain.ContractDomain;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Optional;

public class ContractRepositoryDatabase implements com.petshopadmin.application.port.output.database.ContractRepositoryDatabase {
    public final ContractJPARepository contractJPARepository;

    public ContractRepositoryDatabase (ContractJPARepository contractJPARepository) {
        this.contractJPARepository = contractJPARepository;
    }

    @Override
    public ContractDomain getById(Long id) {
        Optional<ContractDatabase> contract = contractJPARepository.findById(id);

        if (ObjectUtils.isEmpty(contract)) {
            return null;
        }

        return contract.get().toContractDomain();
    }
}
