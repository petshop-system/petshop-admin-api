package com.petshopadmin.adapter.output.repository.database;

import com.petshopadmin.application.domain.ContractDomain;
import com.petshopadmin.application.domain.ServiceDomain;
import com.petshopadmin.exception.NotFoundException;
import com.petshopadmin.utils.converter.ServiceConverterMapper;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServiceRepositoryDatabase implements com.petshopadmin.application.port.output.database.ServiceRepositoryDatabase {

    private final ServiceJPARepository serviceJPARepository;
    private final ServiceConverterMapper serviceConverterMapper;
    private final ContractJPARepository contractJPARepository;

    public ServiceRepositoryDatabase (ServiceJPARepository serviceJPARepository, ContractJPARepository contractJPARepository, ServiceConverterMapper serviceConverterMapper) {
        this.serviceJPARepository = serviceJPARepository;
        this.contractJPARepository = contractJPARepository;
        this.serviceConverterMapper = serviceConverterMapper;
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

    @Override
    public ServiceDomain save(ServiceDomain serviceDomain){
        ServiceDatabase serviceDatabase = serviceConverterMapper.toServiceDatabase(serviceDomain);

        ContractDatabase contractDatabase = null;
        try {
            contractDatabase = contractJPARepository.findById(serviceDomain.getContract().getId())
                    .orElseThrow(() -> new NotFoundException("Contract not found"));
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }

        serviceDatabase.setContract(contractDatabase);

        ServiceDatabase savedService = serviceJPARepository.save(serviceDatabase);
        return savedService.createServiceDomain().build();
    }


}
