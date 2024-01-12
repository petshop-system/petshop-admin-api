package com.petshopadmin.application.service;

import com.petshopadmin.application.domain.ServiceDomain;
import com.petshopadmin.application.port.input.ServiceUserCase;
import com.petshopadmin.application.port.output.database.ServiceRepositoryDatabase;
import com.petshopadmin.exception.InternalServerErrorException;
import com.petshopadmin.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

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

}
