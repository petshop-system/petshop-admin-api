package com.petshopadmin.adapter.output.repository.database;

import com.petshopadmin.application.domain.ServiceDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceJPARepository extends JpaRepository<ServiceDatabase, Long> {

    ServiceDatabase getByIDAndContractID(Long ID, Long contractID);

    List<ServiceDatabase> getByActiveAndContractIDOrderByName(boolean active, Long contractID);
}
