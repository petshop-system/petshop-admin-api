package com.petshopadmin.configuration;

import com.petshopadmin.adapter.output.repository.database.ContractJPARepository;
import com.petshopadmin.adapter.output.repository.database.ServiceJPARepository;
import com.petshopadmin.application.port.input.ServiceUserCase;
import com.petshopadmin.application.port.output.database.ServiceRepositoryDatabase;
import com.petshopadmin.application.service.ServiceService;
import com.petshopadmin.application.service.ValidationService;
import com.petshopadmin.utils.converter.ServiceConverterMapper;
import com.petshopadmin.utils.converter.ServiceConverterMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    ServiceRepositoryDatabase serviceRepositoryDatabase(ServiceJPARepository serviceJPARepository, ContractJPARepository contractJPARepository, ServiceConverterMapper serviceConverterMapper) {
        return new com.petshopadmin.adapter.output.repository.database.ServiceRepositoryDatabase(serviceJPARepository, contractJPARepository, serviceConverterMapper);
    }

    @Bean
    public ServiceConverterMapper serviceConverterMapper() {
        return new ServiceConverterMapperImpl();
    }


    @Bean
    public ValidationService validationService() {
        return new ValidationService();
    }

    @Bean
    ServiceUserCase serviceUserCase (ServiceRepositoryDatabase serviceRepositoryDatabase, ValidationService validationService) {
        return new ServiceService(serviceRepositoryDatabase, validationService);
    }

}
