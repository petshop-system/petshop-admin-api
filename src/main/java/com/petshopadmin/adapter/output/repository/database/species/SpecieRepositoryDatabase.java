package com.petshopadmin.adapter.output.repository.database.species;

import com.petshopadmin.application.domain.SpecieDomain;
import org.apache.commons.lang3.ObjectUtils;

public class SpecieRepositoryDatabase implements com.petshopadmin.application.port.output.database.SpecieRepositoryDatabase {
    private final SpecieJPARepository specieJPARepository;

    public SpecieRepositoryDatabase(SpecieJPARepository specieJPARepository) {
        this.specieJPARepository = specieJPARepository;
    }

    @Override
    public SpecieDomain getByID(Long specieID) {
        SpecieDatabase specieDatabase = specieJPARepository.getByID(specieID);

        if (ObjectUtils.isEmpty(specieDatabase)) {
            return null;
        }

        return specieDatabase.createSpecieDomain().build();
    }

    @Override
    public SpecieDomain getByName(String specieName) {
       SpecieDatabase specieDatabase = specieJPARepository.getByName(specieName);

       if (ObjectUtils.isEmpty(specieDatabase)) {
           return null;
       }

       return specieDatabase.createSpecieDomain().build();
    }

    @Override
    public SpecieDomain save(SpecieDomain specieDomain) {

        SpecieDatabase specieDatabase = new SpecieDatabase();

        if (!ObjectUtils.isEmpty(specieDomain)) {
            specieDatabase.setName(specieDomain.getName());
        }
        SpecieDatabase specieDatabaseSaved = specieJPARepository.save(specieDatabase);

        return specieDatabaseSaved.createSpecieDomain().build();
    }

    @Override
    public void update(Long id, SpecieDomain specieDomain) {

        SpecieDatabase specieDatabase = specieJPARepository.getByID(id);

        specieDatabase.setDisabled(specieDomain.isDisabled());
        specieDatabase.setName(specieDomain.getName());

        this.specieJPARepository.save(specieDatabase);
    }
}
