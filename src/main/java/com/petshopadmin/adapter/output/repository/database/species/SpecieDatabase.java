package com.petshopadmin.adapter.output.repository.database.species;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Table(name = "species", schema = "petshop_api")
@Entity(name = "species")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecieDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name is required")
    private String name;

}
