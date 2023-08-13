package com.example.diver_accelerator.service;

import com.example.diver_accelerator.entity.*;
import com.example.diver_accelerator.repository.DiverRepository;
import com.example.diver_accelerator.repository.EquipmentCatalogRepository;
import com.example.diver_accelerator.repository.VisitRepository;
import com.example.diver_accelerator.repository.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.*;


@Service
public class DiverService {

        @Autowired
        private DiverRepository diverRepository;
       @Autowired
       private DirectionRepository directionRepository;
        @Autowired
        private VisitRepository visitRepository;
        @Autowired
        private EquipmentCatalogRepository equipmentCatalogRepository;

        public List<Diver> allDivers() {
        return diverRepository.findAll();
    }

        public void add(Diver diver) {
        diverRepository.save(diver);
    }

        public void delete(Diver diver) {
        diverRepository.delete(diver);
    }

        @Transactional
        public void deleteVisit(Visit visit) {
        visitRepository.deleteById(visit.getNumber());
    }

        public Diver diverByUser(User user) {
        Diver diver = null;
        try {
            diver = diverRepository.findByUser(user);

        } catch (NoResultException nre) {
        }

        return diver;
    }

        public void deleteDiver(Long userId) {
        if (diverRepository.findById(userId).isPresent()) {
            diverRepository.deleteById(userId);
        }
    }

        public Diver getById(Long id) {
        Diver diver = null;
        try {
            Optional<Diver> diverFromDb = diverRepository.findById(id);
            diver = diverFromDb.orElse(new Diver());
        } catch (NoResultException nre) {
        }

        return diver;
    }


        public boolean checkId(Long id) {
        Optional<Diver> diverFromDb = diverRepository.findById(id);
        return !diverFromDb.isPresent();

    }
        public List<EquipmentCatalog> allEquipments() {
        List<EquipmentCatalog> equipmentCatalogs = null;
        try {
            equipmentCatalogs = equipmentCatalogRepository.findAll();
        } catch (NoResultException nre) {
        }

        return equipmentCatalogs;
    }


        public List<Diver> findTelephone_number(int telephone_number) {
        List<Diver> divers = null;
        try {
            divers = diverRepository.findDiverTelephoneNumber(telephone_number);
        } catch (NoResultException nre) {
        }

        return divers;
    }

    public Set<Diver> getDiversByDirection(Specialization specialization, Boolean status) {
        Set<Diver> Divers = new HashSet<>();

        for (Direction direction : directionRepository.findBySpecializationAndStatus(specialization, status)) {
            Divers.add(direction.getDiver());
        }

        return Divers;

    }

    public List<Diver> getDiversByDirection(Specialization specialization, Boolean status, int telephone_number) {
        List<Diver> Divers = new ArrayList<>();

        for (Direction direction : directionRepository.findBySpecializationAndStatus(specialization, status)) {
            if (direction.getDiver().getTelephone_number() == telephone_number) {
                Divers.add(direction.getDiver());
            }
        }

        return Divers;

    }


    }
