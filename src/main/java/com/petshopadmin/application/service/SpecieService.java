package com.petshopadmin.application.service;

import com.petshopadmin.application.domain.SpecieDomain;
import com.petshopadmin.application.port.input.SpeciesUserCase;
import com.petshopadmin.application.port.output.database.SpecieRepositoryDatabase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import com.petshopadmin.exception.ValidationException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SpecieService implements SpeciesUserCase {
    private final SpecieRepositoryDatabase specieRepositoryDatabase;

    static String SPECIE_INTERNAL_SERVER_ERROR = "species internal server error";
    static String SPECIE_NOT_FOUND = "specie not found";
    static String ILLEGAL_ARGUMENT_NAME_EXCEPTION = "name cannot be null or empty";
    static String ILLEGAL_ARGUMENT_CHARACTERS_MAX_NAME_EXCEPTION = "exceeds maximum length of 255 characters";

    public SpecieService(SpecieRepositoryDatabase specieRepositoryDatabase) {
        this.specieRepositoryDatabase = specieRepositoryDatabase;
    }

    @Override
    public SpecieDomain getByID(Long specieID) throws  InternalServerErrorException, NotFoundException{

        SpecieDomain specieDomain = specieRepositoryDatabase.getByID(specieID);

        if (ObjectUtils.isEmpty(specieDomain)) {
            throw new NotFoundException(SPECIE_NOT_FOUND);
        }

        return specieDomain;
    }

    @Override
    public SpecieDomain getByName(String specieName) throws InternalServerErrorException, NotFoundException {

        if (StringUtils.isBlank(specieName)) {
            throw new InternalServerErrorException(SPECIE_INTERNAL_SERVER_ERROR);
        }

        SpecieDomain specieDomain = specieRepositoryDatabase.getByName(specieName);

        if (ObjectUtils.isEmpty(specieDomain)) {
            throw new NotFoundException(SPECIE_NOT_FOUND);
        }

        return specieDomain;
    }

    @Override
    public SpecieDomain create(SpecieDomain specieDomain) throws InternalServerErrorException, ValidationException {
        this.validate(specieDomain);
        return specieRepositoryDatabase.save(specieDomain);
    }

    @Override
    public void validate(SpecieDomain specieDomain) throws InternalServerErrorException, ValidationException {
        if (ObjectUtils.isEmpty(specieDomain)) {
            throw new InternalServerErrorException(SPECIE_INTERNAL_SERVER_ERROR);
        }

        List<String> errors = new ArrayList<>();

        specieDomain.setName(StringUtils.trimToEmpty(specieDomain.getName()));
        if (ObjectUtils.isEmpty(specieDomain.getName())) {
            errors.add(ILLEGAL_ARGUMENT_NAME_EXCEPTION);
        }

        if (specieDomain.getName().length() > 255) {
            errors.add(ILLEGAL_ARGUMENT_CHARACTERS_MAX_NAME_EXCEPTION);
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public Collection<SpecieDomain> getBy(Map<String, Object> params) throws InternalServerErrorException, ValidationException {
        return specieRepositoryDatabase.getBy(params);
    }

}
