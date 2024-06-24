package com.petshopadmin.utils.converter;

import com.petshopadmin.adapter.input.http.ServiceRequestHTTP;
import com.petshopadmin.application.domain.ContractDomain;
import com.petshopadmin.application.domain.ServiceDomain;
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
}
