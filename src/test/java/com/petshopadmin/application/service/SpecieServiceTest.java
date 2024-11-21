package com.petshopadmin.application.service;

import com.petshopadmin.application.domain.SpecieDomain;
import com.petshopadmin.application.port.input.SpeciesUserCase;
import com.petshopadmin.application.port.output.database.SpecieRepositoryDatabase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import com.petshopadmin.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SpecieServiceTest {

    @MockBean
    SpecieRepositoryDatabase specieRepositoryDatabase;

    SpeciesUserCase getSpecieService() {return new SpecieService(specieRepositoryDatabase);}

    SpecieDomain getDefaultSpecieDomain() {
        SpecieDomain specieDomain = new SpecieDomain();
        specieDomain.setId(1L);
        specieDomain.setName("Felino");
        return specieDomain;
    }

    @Test
    public void getByIDShouldReturnAny() throws InternalServerErrorException, NotFoundException {
        SpecieDomain expectedResult = this.getDefaultSpecieDomain();

        Mockito.when(specieRepositoryDatabase.getByID(Mockito.anyLong())).thenReturn(expectedResult);

        SpeciesUserCase userCase = this.getSpecieService();
        SpecieDomain result = userCase.getByID(1L);

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void getByIDShouldThrowNotFoundException() {
        NotFoundException ex = Assertions.assertThrows(NotFoundException.class, () -> {
            Mockito.when(specieRepositoryDatabase.getByID(Mockito.any())).thenReturn(null);
            SpeciesUserCase usercase = this.getSpecieService();
            usercase.getByID(Mockito.anyLong());
        });

        Assertions.assertEquals(SpecieService.SPECIE_NOT_FOUND, ex.getMessage());
    }

    @Test
    public void getByNameShouldReturnAny() throws InternalServerErrorException, NotFoundException {
        SpecieDomain expectedResult = this.getDefaultSpecieDomain();

        Mockito.when(specieRepositoryDatabase.getByName(Mockito.anyString())).thenReturn(expectedResult);

        SpeciesUserCase usercase = this.getSpecieService();
        SpecieDomain result = usercase.getByName("Felino");

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void getByNameShouldThrowInternalServerErrorException() {
        InternalServerErrorException ex = Assertions.assertThrows(InternalServerErrorException.class, () -> {
            SpeciesUserCase usercase = this.getSpecieService();
            usercase.getByName(null);
        });
        Assertions.assertEquals(SpecieService.SPECIE_INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @Test
    public void getByNameShouldThrowNotFoundException() {
        NotFoundException ex = Assertions.assertThrows(NotFoundException.class, () -> {
           Mockito.when(specieRepositoryDatabase.getByName(Mockito.anyString())).thenReturn(null);

           SpeciesUserCase usercase = this.getSpecieService();
           usercase.getByName("SomeName");
        });

        Assertions.assertEquals(SpecieService.SPECIE_NOT_FOUND, ex.getMessage());
    }


    @Test
    public void createSpecieWithTooLongNameShouldThrowValidationException() {
        SpecieDomain expectedResult = this.getDefaultSpecieDomain();

        String longString = "a".repeat(256);
        expectedResult.setName(longString);

        Assertions.assertThrows(ValidationException.class, () -> {
            SpeciesUserCase userCase =  this.getSpecieService();
            userCase.create(expectedResult);
        });
    }

}
