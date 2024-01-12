package com.petshopadmin.adapter.output.repository.database;

import com.petshopadmin.application.domain.ContractDomain;
import com.petshopadmin.application.domain.ServiceDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service", schema = "petshop_api")
@Entity(name = "service")
@Data
public class ServiceDatabase implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long ID;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "active")
    private boolean active;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_id_contract")
    private ContractDatabase contract;

    @Transient
    private ServiceDomain serviceDomain;

    private void setServiceDomain(ServiceDomain serviceDomain){
        this.serviceDomain = serviceDomain;
    }

    private ServiceDomain getServiceDomain() {
        return this.serviceDomain;
    }

    ServiceDatabase createServiceDomain() {

        this.serviceDomain = new ServiceDomain();
        this.serviceDomain.setId(this.getID());
        this.serviceDomain.setName(this.getName());
        this.serviceDomain.setPrice(this.getPrice());
        this.serviceDomain.setDescription(this.getDescription());
        this.serviceDomain.setActive(this.isActive());

        return this;
    }

    ServiceDatabase addContract() {
        if (!Objects.isNull(this.contract)) {
            this.serviceDomain.setContract(this.contract.toContractDomain());
        }
        return this;
    }

    ServiceDomain build() {
        return this.getServiceDomain();
    }
}
