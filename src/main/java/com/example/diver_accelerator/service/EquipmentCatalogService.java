package com.example.diver_accelerator.service;

import com.example.diver_accelerator.entity.EquipmentCatalog;
import com.example.diver_accelerator.repository.EquipmentCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentCatalogService {

    @Autowired
    private EquipmentCatalogRepository equipmentCatalogRepository;

    @Transactional
    public List<EquipmentCatalog> allEquipment() {
        return equipmentCatalogRepository.findAll();
    }

    @Transactional
    public void add(EquipmentCatalog equipmentCatalog) {
        equipmentCatalogRepository.save(equipmentCatalog);
    }

    @Transactional
    public void delete(EquipmentCatalog equipmentCatalog) {
        equipmentCatalogRepository.delete(equipmentCatalog);
    }

    public boolean checkID(String ID) {
        Optional<EquipmentCatalog> equipmentCatalogFromDb = equipmentCatalogRepository.findById(String.valueOf(ID));
        return !equipmentCatalogFromDb.isPresent();
    }

   public EquipmentCatalog getById(String ID) {

        Optional<EquipmentCatalog> equipmentCatalogFromDb = equipmentCatalogRepository.findById(ID);
        return equipmentCatalogFromDb.orElse(new EquipmentCatalog());

    }
}
