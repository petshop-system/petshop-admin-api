package com.petshopadmin.adapter.input.http.species;

import com.petshopadmin.adapter.input.http.ResponseHTTP;
import com.petshopadmin.application.domain.SpecieDomain;
import com.petshopadmin.application.port.input.SpeciesUserCase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "species")
public class SpecieController {

    private final SpeciesUserCase speciesUserCase;

    public SpecieController(SpeciesUserCase speciesUserCase) {
        this.speciesUserCase = speciesUserCase;
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseHTTP getByID(@PathVariable(name = "id", required = true) Long speciesID)
            throws InternalServerErrorException, NotFoundException {
        SpecieDomain specieDomain = speciesUserCase.getByID(speciesID);

        return new ResponseHTTP("success to get species by id", new SpecieResponseHTTP(specieDomain), null, LocalDateTime.now());
    }
}
