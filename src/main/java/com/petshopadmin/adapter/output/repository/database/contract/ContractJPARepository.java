package com.petshopadmin.adapter.output.repository.database.contract;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractJPARepository extends JpaRepository<ContractDatabase, Long> {
    ContractDatabase getByID(Long id);
}

