package com.petshopadmin.application.port.input;

import com.petshopadmin.application.domain.SpecieDomain;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import com.petshopadmin.exception.ValidationException;

import java.util.Collection;
import java.util.Map;

public interface SpeciesUserCase {
    SpecieDomain create(SpecieDomain specieDomain) throws InternalServerErrorException, ValidationException;
    SpecieDomain getByID(Long specieID) throws InternalServerErrorException, NotFoundException;
    SpecieDomain getByName(String specieName) throws InternalServerErrorException, NotFoundException;
    void validate(SpecieDomain specieDomain) throws  InternalServerErrorException, ValidationException;
    Collection<SpecieDomain> getBy(Map<String, Object> params) throws  InternalServerErrorException, ValidationException;
}
