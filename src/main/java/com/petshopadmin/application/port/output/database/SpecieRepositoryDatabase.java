package com.petshopadmin.application.port.output.database;

import com.petshopadmin.application.domain.SpecieDomain;

import java.util.Collection;
import java.util.Map;

public interface SpecieRepositoryDatabase {
    SpecieDomain getByID(Long specieID);
    SpecieDomain getByName(String specieName);
    SpecieDomain save(SpecieDomain specieDomain);
    Collection<SpecieDomain> getBy(Map<String, Object> params);
}
