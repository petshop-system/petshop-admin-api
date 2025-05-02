package com.petshopadmin.application.port.input;

import com.petshopadmin.application.domain.SpecieDomain;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import com.petshopadmin.exception.ValidationException;

public interface SpeciesUserCase {
    SpecieDomain create(SpecieDomain specieDomain) throws InternalServerErrorException, ValidationException;
    SpecieDomain getByID(Long specieID) throws InternalServerErrorException, NotFoundException;
    SpecieDomain getByName(String specieName) throws InternalServerErrorException, NotFoundException;
    void validate(SpecieDomain specieDomain) throws  InternalServerErrorException, ValidationException;
    void update(Long id, SpecieDomain specieDomain) throws InternalServerErrorException, NotFoundException;
}
