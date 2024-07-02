package com.petshopadmin.application.service;

import com.petshopadmin.application.domain.ContractDomain;
import com.petshopadmin.application.domain.ServiceDomain;
import com.petshopadmin.application.port.input.ServiceUserCase;
import com.petshopadmin.application.port.output.database.ServiceRepositoryDatabase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
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
    public void createServiceWithNullServiceDomain() {
        Assertions.assertThrows(InternalServerErrorException.class, () -> {
            getServiceService().create(null);
        });
    }

    @Test
    public void createServiceWithTooLongNameOrDescription() {
        String longString = "a".repeat(256);
        ServiceDomain invalidName = new ServiceDomain();
        invalidName.setName(longString);
        invalidName.setDescription("validDescription");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            getServiceService().create(invalidName);
        });

        ServiceDomain invalidDescription = new ServiceDomain();
        invalidDescription.setDescription(longString);
        invalidDescription.setName("ValidName");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            getServiceService().create(invalidDescription);
        });

    }

    @Test
    public void createServiceWithContract() {
        ContractDomain contract = new ContractDomain();
        contract.setId(1L);

        ServiceDomain service = new ServiceDomain();
        service.setName("validName");
        service.setDescription("validDescription");
        service.setContract(contract);

        Assertions.assertDoesNotThrow(() -> {
            getServiceService().create(service);
        });

        try {
            ServiceDomain createdService = getServiceService().create(service);
            Assertions.assertEquals(contract.getId(), createdService.getContract().getId());
        } catch (NotFoundException | InternalServerErrorException e) {
            Assertions.fail("Exception thrown during service creation: " + e.getMessage());
        }
    }
}
