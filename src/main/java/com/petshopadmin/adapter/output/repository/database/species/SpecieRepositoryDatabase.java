package com.petshopadmin.adapter.output.repository.database.species;

import com.petshopadmin.application.domain.SpecieDomain;

public class SpecieRepositoryDatabase implements com.petshopadmin.application.port.output.database.SpecieRepositoryDatabase {
   private final SpecieJPARepository specieJPARepository;

   public SpecieRepositoryDatabase (SpecieJPARepository specieJPARepository) {
       this.specieJPARepository = specieJPARepository;
   }

    @Override
    public SpecieDomain getByID(Long id) {return null;}
}
