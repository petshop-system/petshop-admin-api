package com.petshopadmin.adapter.input.http.species;

import com.petshopadmin.adapter.input.http.ResponseHTTP;
import com.petshopadmin.application.domain.SpecieDomain;
import com.petshopadmin.application.port.input.SpeciesUserCase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import com.petshopadmin.exception.ValidationException;
import com.petshopadmin.utils.converter.SpecieConverterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping(path = "species", produces =  MediaType.APPLICATION_JSON_VALUE)
public class SpecieController {

    private static final Logger logger = LoggerFactory.getLogger(SpecieController.class);

    private final SpeciesUserCase speciesUserCase;
    private final SpecieConverterMapper specieConverterMapper;

    public SpecieController(SpeciesUserCase speciesUserCase, SpecieConverterMapper specieConverterMapper)
    {
        this.speciesUserCase = speciesUserCase;
        this.specieConverterMapper = specieConverterMapper;
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "id/{id}")
    public ResponseHTTP getByID(@PathVariable(name = "id", required = true) Long speciesID)
            throws InternalServerErrorException, NotFoundException
    {
        SpecieDomain specieDomain = speciesUserCase.getByID(speciesID);

        logger.info("Request to get specie id: {}", speciesID);

        return new ResponseHTTP("success to get species by id", new SpecieResponseHTTP(specieDomain), null, LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "name/{name}")
    public ResponseHTTP getByName(@PathVariable(name = "name", required = true) String specieName)
            throws InternalServerErrorException, NotFoundException {

        SpecieDomain specieDomain = speciesUserCase.getByName(specieName);
        logger.info("Request to get specie name: {}", specieDomain.getName());

        return new ResponseHTTP("success to get species by name", new SpecieResponseHTTP(specieDomain), null, LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = {"", "/"})
    public ResponseHTTP create(@RequestBody SpecieRequestHTTP specieRequestHTTP)
            throws InternalServerErrorException, ValidationException {

        logger.info("Request to create specie: {}", specieRequestHTTP.name());
        try {
            SpecieDomain specieDomain = specieConverterMapper.toSpecieDomain(specieRequestHTTP);

            speciesUserCase.create(specieDomain);
            logger.info("Specie created: {}", specieDomain.getName());

            return new ResponseHTTP("sucess to create a new species",  new SpecieResponseHTTP(specieDomain), null, LocalDateTime.now());
        } catch (ValidationException e) {
            return new ResponseHTTP("Errors Found", null, Arrays.asList(e.getMessages().toArray()), LocalDateTime.now());
        }
    }
}