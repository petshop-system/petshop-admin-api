package com.petshopadmin.utils.converter;

import com.petshopadmin.adapter.input.http.ServiceRequestHTTP;
import com.petshopadmin.application.domain.ServiceDomain;

public interface ServiceConverterMapper {

    ServiceDomain toServiceDomain(ServiceRequestHTTP source);
}
