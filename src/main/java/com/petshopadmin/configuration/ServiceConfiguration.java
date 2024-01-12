package com.petshopadmin.configuration;

import com.petshopadmin.adapter.output.repository.database.ServiceJPARepository;
import com.petshopadmin.application.port.input.ServiceUserCase;
import com.petshopadmin.application.port.output.database.ServiceRepositoryDatabase;
import com.petshopadmin.application.service.ServiceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    ServiceRepositoryDatabase serviceRepositoryDatabase(ServiceJPARepository serviceJPARepository) {
        return new com.petshopadmin.adapter.output.repository.database.ServiceRepositoryDatabase(serviceJPARepository);
    }

    @Bean
    ServiceUserCase serviceUserCase (ServiceRepositoryDatabase serviceRepositoryDatabase) {
        return new ServiceService(serviceRepositoryDatabase);
    }

}
