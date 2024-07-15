package com.petshopadmin.utils.converter;

import com.petshopadmin.adapter.input.http.ServiceRequestHTTP;
import com.petshopadmin.adapter.output.repository.database.ContractDatabase;
import com.petshopadmin.adapter.output.repository.database.ServiceDatabase;
import com.petshopadmin.application.domain.ContractDomain;
import com.petshopadmin.application.domain.ServiceDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ServiceConverterMapper {

    @Mapping(target = "contract.id", source = "contractid")
    ServiceDomain toServiceDomain(ServiceRequestHTTP source);

    @Mapping(target = "ID", source = "contract.id")
    ServiceDatabase toServiceDatabase(ServiceDomain source);

    ContractDomain toContractDomain(Long id);

    @Mapping(target = "ID", source = "id")
    ContractDatabase toContractDatabase(ContractDomain source);
}
