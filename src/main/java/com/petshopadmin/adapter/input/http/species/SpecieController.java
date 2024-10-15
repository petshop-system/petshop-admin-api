package com.petshopadmin.adapter.input.http.species;

import com.petshopadmin.adapter.input.http.ResponseHTTP;
import com.petshopadmin.application.domain.SpecieDomain;
import com.petshopadmin.application.port.input.SpeciesUserCase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import com.petshopadmin.exception.ValidationException;
import com.petshopadmin.utils.converter.SpecieConverterMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping(path = "species")
public class SpecieController {

    private final SpeciesUserCase speciesUserCase;
    private final SpecieConverterMapper specieConverterMapper;

    public SpecieController(SpeciesUserCase speciesUserCase, SpecieConverterMapper specieConverterMapper)
    {
        this.speciesUserCase = speciesUserCase;
        this.specieConverterMapper = specieConverterMapper;
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseHTTP getByID(@PathVariable(name = "id", required = true) Long speciesID)
            throws InternalServerErrorException, NotFoundException
    {
        SpecieDomain specieDomain = speciesUserCase.getByID(speciesID);

        return new ResponseHTTP("success to get species by id", new SpecieResponseHTTP(specieDomain), null, LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseHTTP create(@RequestBody SpecieRequestHTTP specieRequestHTTP)
            throws InternalServerErrorException, ValidationException {
        try {
            SpecieDomain specieDomain = specieConverterMapper.toSpecieDomain(specieRequestHTTP);

            speciesUserCase.create(specieDomain);
            return new ResponseHTTP("sucess to create a new species",  new SpecieResponseHTTP(specieDomain), null, LocalDateTime.now());
        } catch (ValidationException e) {
            return new ResponseHTTP("Errors Found", null, Arrays.asList(e.getMessages().toArray()), LocalDateTime.now());
        }
    }
}