package com.petshopadmin.adapter.output.repository.database;

import com.petshopadmin.application.domain.ServiceDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceJPARepository extends JpaRepository<ServiceDatabase, Long> {

    ServiceDatabase getByIDAndContractID(Long contractID, Long ID);

    List<ServiceDatabase> getByActiveAndContractID(boolean active, Long contractID);
}
