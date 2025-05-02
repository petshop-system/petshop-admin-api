package com.petshopadmin.application.port.output.database;

import com.petshopadmin.application.domain.SpecieDomain;

public interface SpecieRepositoryDatabase {
    SpecieDomain getByID(Long specieID);
    SpecieDomain save(SpecieDomain specieDomain);
    void update(Long id,SpecieDomain specieDomain);
}
