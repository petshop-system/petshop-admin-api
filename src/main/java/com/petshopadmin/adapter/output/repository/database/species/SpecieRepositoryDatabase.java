package com.petshopadmin.adapter.output.repository.database.species;

import com.petshopadmin.application.domain.SpecieDomain;
import com.petshopadmin.utils.converter.SpecieConverterMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.*;

public class SpecieRepositoryDatabase implements
        com.petshopadmin.application.port.output.database.SpecieRepositoryDatabase {
   private final SpecieJPARepository specieJPARepository;

   private final SpecieConverterMapper specieConverterMapper;

   public SpecieRepositoryDatabase (SpecieJPARepository specieJPARepository,
                                    SpecieConverterMapper specieConverterMapper) {
       this.specieJPARepository = specieJPARepository;
       this.specieConverterMapper = specieConverterMapper;
   }

    @Override
    public SpecieDomain getByID(Long id) {
       SpecieDatabase specieDatabase = specieJPARepository.getById(id);

        if (ObjectUtils.isEmpty(specieDatabase)) {
            return null;
        }

       return specieConverterMapper.to(specieDatabase);
    }

    @Override
    public SpecieDomain getByName(String specieName) {
       SpecieDatabase specieDatabase = specieJPARepository.getByName(specieName);

       if (ObjectUtils.isEmpty(specieDatabase)) {
           return null;
       }

       return specieConverterMapper.to(specieDatabase);
    }

    @Override
    public SpecieDomain save(SpecieDomain specieDomain) {

       SpecieDatabase specieDatabase = new SpecieDatabase();

       if (!ObjectUtils.isEmpty(specieDomain)) {
           specieDatabase.setName(specieDomain.getName());
       }
       SpecieDatabase specieDatabaseSaved = specieJPARepository.save(specieDatabase);

       return specieConverterMapper.to(specieDatabaseSaved);
    }

    @Override
    public Collection<SpecieDomain> getBy(Map<String, Object> params) {

        Specification<SpecieDatabase> spec = Specification
                .where(SpecieSpecifications.defaultWhereParam());

        if (ObjectUtils.isNotEmpty(params.get("name")))
            spec.and(SpecieSpecifications
                    .containName(String.valueOf(params.get("name"))));

        Collection<SpecieDomain> specieDomains = new ArrayList<>();
        specieJPARepository.findAll(spec)
                .parallelStream()
                .forEach(specieDatabase -> {
                    specieDomains.add(specieConverterMapper.to(specieDatabase));
                });

        return specieDomains;
    }
}
