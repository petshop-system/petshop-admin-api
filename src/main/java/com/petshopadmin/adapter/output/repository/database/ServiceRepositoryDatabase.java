package com.petshopadmin.adapter.output.repository.database;

import com.petshopadmin.application.domain.ServiceDomain;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ServiceRepositoryDatabase implements com.petshopadmin.application.port.output.database.ServiceRepositoryDatabase {

    private final ServiceJPARepository serviceJPARepository;

    public ServiceRepositoryDatabase (ServiceJPARepository serviceJPARepository) {
        this.serviceJPARepository = serviceJPARepository;
    }

    @Override
    public ServiceDomain getByID(Long contractID, Long ID) {

        ServiceDatabase serviceDatabase = serviceJPARepository.getByIDAndContractID(ID, contractID);
        if (ObjectUtils.isEmpty(serviceDatabase))
            return null;

        return serviceDatabase.
                createServiceDomain().
                addContract()
                .build();
    }

    @Override
    public List<ServiceDomain> getByActive(Long contractID, boolean active) {

        List<ServiceDatabase> list = serviceJPARepository.
                getByActiveAndContractIDOrderByName(active, contractID);

        if (Objects.isNull(list))
            return new ArrayList<>();

        List<ServiceDomain> result = new ArrayList<>();
        list.stream().forEach(value -> result.add(value.createServiceDomain().build()));

        return result;
    }
}
