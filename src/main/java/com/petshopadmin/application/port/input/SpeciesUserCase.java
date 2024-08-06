package com.petshopadmin.application.port.input;

import com.petshopadmin.application.domain.SpecieDomain;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;

public interface SpeciesUserCase {
    SpecieDomain getByID(Long specieID) throws InternalServerErrorException, NotFoundException;
}
