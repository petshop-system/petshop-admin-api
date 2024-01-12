package com.petshopadmin.application.port.output.database;

import com.petshopadmin.application.domain.ServiceDomain;

import java.util.List;

public interface ServiceRepositoryDatabase {

    ServiceDomain getByID(Long contractID, Long ID);

    List<ServiceDomain> getByActive(Long contractID, boolean active);

}
