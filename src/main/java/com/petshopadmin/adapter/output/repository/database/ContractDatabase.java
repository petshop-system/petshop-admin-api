package com.petshopadmin.adapter.output.repository.database;

import com.petshopadmin.application.domain.ContractDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "contract")
@Table(name = "contract", schema = "petshop_api")
@Data
public class ContractDatabase implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long ID;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    ContractDomain toContractDomain () {

        ContractDomain contractDomain = new ContractDomain();
        contractDomain.setId(this.getID());
        contractDomain.setEmail(this.getEmail());
        contractDomain.setDateCreated(this.getDateCreated());
        contractDomain.setName(this.getName());

        return contractDomain;
    }

}
