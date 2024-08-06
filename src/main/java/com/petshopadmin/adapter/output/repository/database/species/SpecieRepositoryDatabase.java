package com.petshopadmin.adapter.output.repository.database.species;

import com.petshopadmin.application.domain.SpecieDomain;
import org.apache.commons.lang3.ObjectUtils;

public class SpecieRepositoryDatabase implements com.petshopadmin.application.port.output.database.SpecieRepositoryDatabase {
   private final SpecieJPARepository specieJPARepository;

   public SpecieRepositoryDatabase (SpecieJPARepository specieJPARepository) {
       this.specieJPARepository = specieJPARepository;
   }

    @Override
    public SpecieDomain getByID(Long specieID) {
       if (ObjectUtils.isEmpty(specieID)) {
           return null;
       }

       SpecieDatabase specieDatabase = specieJPARepository.getByID(specieID);

       return specieDatabase.createSpecieDomain().build();
    }
}
