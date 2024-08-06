package com.petshopadmin.application.service;

import com.petshopadmin.application.domain.SpecieDomain;
import com.petshopadmin.application.port.input.SpeciesUserCase;
import com.petshopadmin.application.port.output.database.SpecieRepositoryDatabase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;

public class SpecieService implements SpeciesUserCase {
    private final SpecieRepositoryDatabase specieRepositoryDatabase;

    static String SPECIE_INTERNAL_SERVER_ERROR = "specie internal server error";
    static String SPECIE_NOT_FOUND = "specie not found";

    public SpecieService(SpecieRepositoryDatabase specieRepositoryDatabase) {
        this.specieRepositoryDatabase = specieRepositoryDatabase;
    }

    @Override
    public SpecieDomain getByID(Long specieID) throws  InternalServerErrorException, NotFoundException{
        if (ObjectUtils.isEmpty(specieID)) {
            throw new InternalServerErrorException(SPECIE_INTERNAL_SERVER_ERROR);
        }

        SpecieDomain specieDomain = specieRepositoryDatabase.getByID(specieID);

        if (ObjectUtils.isEmpty(specieDomain)) {
            throw new NotFoundException(SPECIE_NOT_FOUND);
        }

        return specieDomain;
    }

}
