package com.petshopadmin.adapter.output.repository.database.species;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "species", schema = "petshop_api")
@Entity(name = "species")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecieDatabase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long ID;

    @Column(name = "name")
    @NotEmpty(message = "Name is required")
    private String name;
}
