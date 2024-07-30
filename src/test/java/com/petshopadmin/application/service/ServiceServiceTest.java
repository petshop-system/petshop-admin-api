package com.petshopadmin.application.service;

import com.petshopadmin.application.domain.ContractDomain;
import com.petshopadmin.application.domain.ServiceDomain;
import com.petshopadmin.application.port.input.ServiceUserCase;
import com.petshopadmin.application.port.output.database.ServiceRepositoryDatabase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import com.petshopadmin.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@ExtendWith(SpringExtension.class)
public class ServiceServiceTest {

    @MockBean
    ServiceRepositoryDatabase serviceRepositoryDatabase;

    ServiceUserCase getServiceService() {
        return new ServiceService(serviceRepositoryDatabase);
    }

    ServiceDomain getDefaultServiceDomain() {
        ServiceDomain domain = new ServiceDomain();
        domain.setId(1L);
        domain.setName("test");
        domain.setActive(true);
        domain.setPrice(BigDecimal.TEN);
        domain.setDescription("test description");
        return domain;
    }

    @Test
    public void getByIDShouldReturnAny() throws NotFoundException, InternalServerErrorException {

        ServiceDomain expectedResult = this.getDefaultServiceDomain();
        Mockito.when(serviceRepositoryDatabase.getByID(Mockito.any(), Mockito.any())).
                thenReturn(expectedResult);

        ServiceUserCase userCase = this.getServiceService();
        ServiceDomain result = userCase.getByID(1L, 1L);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void getByIDShouldThrowInternalServerErrorException() {

        InternalServerErrorException ex = Assertions.assertThrows(InternalServerErrorException.class, () -> {
            ServiceUserCase userCase = this.getServiceService();
            userCase.getByID(null, null);
        });

        Assertions.assertEquals(ServiceService.SERVICE_INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @Test
    public void getByIDShouldThrowNotFoundException() {

        NotFoundException ex = Assertions.assertThrows(NotFoundException.class, () -> {
            Mockito.when(serviceRepositoryDatabase.getByID(Mockito.any(), Mockito.any())).
                    thenReturn(null);
            ServiceUserCase userCase = this.getServiceService();
            userCase.getByID(Mockito.anyLong(), Mockito.anyLong());
        });

        Assertions.assertEquals(ServiceService.SERVICE_NOT_FOUND, ex.getMessage());
    }

    @Test
    public void getByActiveShouldReturnAny() throws NotFoundException, InternalServerErrorException {

        List<ServiceDomain> expectedResult = new ArrayList<>();
        expectedResult.add(this.getDefaultServiceDomain());
        Mockito.when(serviceRepositoryDatabase.getByActive(Mockito.anyLong(), Mockito.anyBoolean()))
                .thenReturn(expectedResult);
        List<ServiceDomain> list = this.getServiceService().getByActive(Mockito.anyLong(), Mockito.anyBoolean());
        Assertions.assertTrue(list.size() > 0);

    }

    @Test
    public void createServiceShouldThrowInternalServerError() {
        ServiceDomain serviceDomain = null;

        InternalServerErrorException ex = Assertions.assertThrows(InternalServerErrorException.class, () -> {
            Mockito.when(serviceRepositoryDatabase.save(Mockito.any(ServiceDomain.class))).thenReturn(null);

            ServiceUserCase userCase = this.getServiceService();
            userCase.create(serviceDomain);
        });

        Assertions.assertEquals(ServiceService.SERVICE_INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @Test
    public void createServiceWithTooLongNameShouldThrowValidationException() {
        ServiceDomain expectedResult = this.getDefaultServiceDomain();

        String longString = "a".repeat(256);
        expectedResult.setName(longString);

        Assertions.assertThrows(ValidationException.class, () -> {
            ServiceUserCase userCase =  this.getServiceService();
            userCase.create(expectedResult);
        });
    }

    @Test
    public void createServiceWithTooLongDescriptionShouldThrowValidationException() {
        ServiceDomain expectedResult = this.getDefaultServiceDomain();

        String longString = "a".repeat(256);
        expectedResult.setDescription(longString);


        Assertions.assertThrows(ValidationException.class, () -> {
            ServiceUserCase userCase = this.getServiceService();
            userCase.create(expectedResult);
        });
    }

    @Test
    public void createServiceWithInvalidPriceShouldThrowValidationException() {
        ServiceDomain expectedResult = this.getDefaultServiceDomain();
        expectedResult.setPrice(BigDecimal.valueOf(-1));

        Assertions.assertThrows(ValidationException.class, () -> {
            ServiceUserCase userCase = this.getServiceService();
            userCase.create(expectedResult);
        });
    }

    @Test
    public void createServiceWithValidContractShouldReturnAny() throws ValidationException, NotFoundException, InternalServerErrorException {
        ServiceDomain expectedResult = this.getDefaultServiceDomain();

        ContractDomain contract = new ContractDomain();
        contract.setId(1L);
        expectedResult.setContract(contract);

        Mockito.when(serviceRepositoryDatabase.save(Mockito.any(ServiceDomain.class))).thenReturn(expectedResult);

        ServiceUserCase userCase = this.getServiceService();
        ServiceDomain result = userCase.create(expectedResult);
        Assertions.assertEquals(expectedResult, result);

    }
}
