package com.petshopadmin.application.service;

import com.petshopadmin.application.domain.SpecieDomain;
import com.petshopadmin.application.port.output.database.SpecieRepositoryDatabase;

public class SpecieService {
    private final SpecieRepositoryDatabase specieRepositoryDatabase;

    public SpecieService(SpecieRepositoryDatabase specieRepositoryDatabase) {
        this.specieRepositoryDatabase = specieRepositoryDatabase;
    }

}
