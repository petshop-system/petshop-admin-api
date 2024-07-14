package com.petshopadmin.adapter.output.repository.database;

import com.petshopadmin.application.domain.ContractDomain;
import com.petshopadmin.exception.NotFoundException;
import com.petshopadmin.utils.converter.ServiceConverterMapper;
import org.apache.commons.lang3.ObjectUtils;

public class ContractRepositoryDatabase implements com.petshopadmin.application.port.output.database.ContractRepositoryDatabase {
    public final ContractJPARepository contractJPARepository;
    public final ServiceConverterMapper serviceConverterMapper;

    public ContractRepositoryDatabase (ContractJPARepository contractJPARepository, ServiceConverterMapper serviceConverterMapper) {
        this.contractJPARepository = contractJPARepository;
        this.serviceConverterMapper = serviceConverterMapper;
    }

    @Override
    public ContractDomain getById(Long id){

        ContractDatabase contractDatabase = contractJPARepository.getById(id);

        if (ObjectUtils.isEmpty(contractDatabase)) {
            return null;
        }
        return contractDatabase.toContractDomain();
    }
}
