package com.petshopadmin.utils.converter;

import com.petshopadmin.adapter.input.http.species.SpecieRequestHTTP;
import com.petshopadmin.adapter.output.repository.database.species.SpecieDatabase;
import com.petshopadmin.application.domain.SpecieDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SpecieConverterMapper {

    @Mapping(target = "id" , source = "specieid")
    SpecieDomain toSpecieDomain(SpecieRequestHTTP source);
    SpecieDomain to(SpecieDatabase specieDatabase);
}
