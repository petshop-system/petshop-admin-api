package com.petshopadmin.utils.converter;

import com.petshopadmin.adapter.input.http.species.SpecieRequestHTTP;
import com.petshopadmin.application.domain.SpecieDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SpecieConverterMapper {

    @Mapping(target = "id" , source = "specieid")
    SpecieDomain toSpecieDomain(SpecieRequestHTTP source);
}
