package com.petshopadmin.utils.converter;

import com.petshopadmin.adapter.input.http.ServiceRequestHTTP;
import com.petshopadmin.adapter.output.repository.database.ContractDatabase;
import com.petshopadmin.adapter.output.repository.database.ServiceDatabase;
import com.petshopadmin.application.domain.ContractDomain;
import com.petshopadmin.application.domain.ServiceDomain;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

@Component
public class ServiceConverterMapperImpl implements ServiceConverterMapper{

    @Override
    public ServiceDomain toServiceDomain(ServiceRequestHTTP source) {
        ServiceDomain domain = new ServiceDomain();

        domain.setName(source.name());
        domain.setDescription(source.description());
        domain.setPrice(source.price());
        domain.setActive(source.active());

        ContractDomain contract = new ContractDomain();
        contract.setId(source.contractId());

        domain.setContract(contract);
        return domain;
    }

    @Override
    public ServiceDatabase toServiceDatabase(ServiceDomain source) {
        ServiceDatabase database = new ServiceDatabase(source);

        if (!ObjectUtils.isEmpty(source.getContract()) && !ObjectUtils.isEmpty(source.getContract().getId())) {
            ContractDatabase contract = new ContractDatabase();
            contract.setID(source.getContract().getId());

            database.setContract(contract);
        }

        return database;
    }
}
