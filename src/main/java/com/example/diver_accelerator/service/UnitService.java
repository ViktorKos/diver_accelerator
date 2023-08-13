package com.example.diver_accelerator.service;

import com.example.diver_accelerator.entity.Unit;
import com.example.diver_accelerator.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    @Transactional
    public List<Unit> allUnit() {
        List<Unit> units = unitRepository.findAll();
        units.sort(Comparator.comparing(Unit::getName));
        return units;
    }

    @Transactional
    public void add(Unit unit) {
        unitRepository.save(unit);
    }

    @Transactional
    public void delete(Unit unit) {
        unitRepository.delete(unit);
    }


    public Unit getById(String id) {
        Optional<Unit> unitFromDb = unitRepository.findById(id);
        return unitFromDb.orElse(new Unit());

    }

}
