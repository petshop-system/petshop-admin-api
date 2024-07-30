package com.petshopadmin.utils.converter;

import com.petshopadmin.adapter.input.http.ServiceRequestHTTP;
import com.petshopadmin.application.domain.ServiceDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ServiceConverterMapper {

    @Mapping(target = "contract.id", source = "contractid")
    ServiceDomain toServiceDomain(ServiceRequestHTTP source);
}

