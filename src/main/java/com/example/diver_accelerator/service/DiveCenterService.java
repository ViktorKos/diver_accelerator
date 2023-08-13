package com.example.diver_accelerator.service;

import com.example.diver_accelerator.entity.DiveCenter;
import com.example.diver_accelerator.repository.DiveCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DiveCenterService {

    @Autowired
    private DiveCenterRepository diveCenterRepository;

    @Transactional
    public List<DiveCenter> allDiveCenter() {
        List<DiveCenter> diveCenters = diveCenterRepository.findAll();
        diveCenters.sort(Comparator.comparing(DiveCenter::getName));
        return diveCenters;
    }

    @Transactional
    public List<DiveCenter> allDiveCenterWithoutUnit() {
        List<DiveCenter> diveCenters = diveCenterRepository.findByUnitNull();
        diveCenters.sort(Comparator.comparing(DiveCenter::getName));
        return diveCenters;
    }

    @Transactional
    public void add(DiveCenter diveCenter) {
        diveCenterRepository.save(diveCenter);
    }

    @Transactional
    public void delete(DiveCenter diveCenter) {
        diveCenterRepository.delete(diveCenter);
    }


    public DiveCenter getById(String id) {
        Optional<DiveCenter> diveCenterFromDb = diveCenterRepository.findById(id);
        return diveCenterFromDb.orElse(new DiveCenter());

    }

    public boolean checkId(String id) {
        Optional<DiveCenter> diveCenterFromDb = diveCenterRepository.findById(id);
        return !diveCenterFromDb.isPresent();
    }


}
