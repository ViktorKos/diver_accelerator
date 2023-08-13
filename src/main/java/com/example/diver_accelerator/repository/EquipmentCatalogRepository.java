package com.example.diver_accelerator.repository;

import com.example.diver_accelerator.entity.EquipmentCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentCatalogRepository extends JpaRepository<EquipmentCatalog, String> {

}
