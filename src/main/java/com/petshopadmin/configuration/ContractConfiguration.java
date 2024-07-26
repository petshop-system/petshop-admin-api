package com.petshopadmin.configuration;

import com.petshopadmin.adapter.output.repository.database.ContractJPARepository;
import com.petshopadmin.adapter.output.repository.database.ContractRepositoryDatabase;
import com.petshopadmin.application.port.input.ContractUserCase;
import com.petshopadmin.application.service.ContractService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractConfiguration {

    @Bean
    ContractRepositoryDatabase contractRepositoryDatabase(ContractJPARepository contractJPARepository){
        return new ContractRepositoryDatabase(contractJPARepository);
    }

    @Bean
    ContractUserCase contractUserCase(ContractRepositoryDatabase contractRepositoryDatabase){
        return  new ContractService(contractRepositoryDatabase);
    }

}
