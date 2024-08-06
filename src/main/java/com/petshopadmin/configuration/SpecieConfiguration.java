package com.petshopadmin.configuration;

import com.petshopadmin.adapter.output.repository.database.species.SpecieJPARepository;
import com.petshopadmin.adapter.output.repository.database.species.SpecieRepositoryDatabase;
import com.petshopadmin.application.port.input.SpeciesUserCase;
import com.petshopadmin.application.service.SpecieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpecieConfiguration {

    @Bean
    SpecieRepositoryDatabase specieRepositoryDatabase(SpecieJPARepository specieJPARepository) {
        return new SpecieRepositoryDatabase(specieJPARepository);
    }

    @Bean
    SpeciesUserCase speciesUserCase(SpecieRepositoryDatabase specieRepositoryDatabase) {
        return new SpecieService(specieRepositoryDatabase);
    }
}
