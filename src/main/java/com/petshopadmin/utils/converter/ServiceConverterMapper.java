package com.petshopadmin.utils.converter;

import com.petshopadmin.adapter.input.http.ServiceRequestHTTP;
import com.petshopadmin.adapter.output.repository.database.ServiceDatabase;
import com.petshopadmin.application.domain.ServiceDomain;

public interface ServiceConverterMapper {

    ServiceDomain toServiceDomain(ServiceRequestHTTP source);
    ServiceDatabase toServiceDatabase(ServiceDomain source);
}
