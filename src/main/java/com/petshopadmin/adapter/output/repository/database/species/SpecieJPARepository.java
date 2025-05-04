package com.petshopadmin.adapter.output.repository.database.species;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpecieJPARepository extends JpaRepository<SpecieDatabase, Long>,
        JpaSpecificationExecutor<SpecieDatabase> {
    SpecieDatabase getById(Long id);
    SpecieDatabase getByName(String specieName);
}
