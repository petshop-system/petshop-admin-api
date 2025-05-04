package com.petshopadmin.adapter.output.repository.database.species;

import org.springframework.data.jpa.domain.Specification;

public class SpecieSpecifications {

    public static Specification<SpecieDatabase> containName(String name) {
        return (root, query, cb) -> name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<SpecieDatabase> defaultWhereParam() {
        return (root, query, cb) -> cb.isNotNull(root.get("id"));
    }

}
