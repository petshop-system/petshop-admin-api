package com.petshopadmin.adapter.output.repository.database.species;

import com.petshopadmin.application.domain.SpecieDomain;
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
    private Long ID;

    @Column(name = "name")
    @NotEmpty(message = "Name is required")
    private String name;

    @Transient
    private SpecieDomain specieDomain;

    private void setSpecieDomain(SpecieDomain specieDomain) {
        this.specieDomain = specieDomain;
    }

    private SpecieDomain getSpecieDomain() {
        return this.specieDomain;
    }

    SpecieDatabase createSpecieDomain() {
        this.specieDomain = new SpecieDomain();
        this.specieDomain.setId(this.ID);
        this.specieDomain.setName(this.getName());

        return this;
    }

    SpecieDomain build(){
        return this.getSpecieDomain();
    }
}
