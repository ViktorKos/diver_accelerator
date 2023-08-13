package com.example.diver_accelerator.entity;

import com.example.diver_accelerator.repository.EquipmentCatalogRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "equipment_catalog")
public class EquipmentCatalog  {

    @Id
    @Size(max=100)
    private String Id;
    private String name;

}
