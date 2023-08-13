package com.example.diver_accelerator.service;

import com.example.diver_accelerator.entity.Immersion;
import com.example.diver_accelerator.repository.ImmersionRepository;
import com.example.diver_accelerator.transientClasses.ImmersionStatistic;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ImmersionService {

    @Autowired
    private ImmersionRepository immersionRepository;

    @Transactional
    public List<Immersion> allImmersion() {
        return immersionRepository.findAll();
    }

    @Transactional
    public void add(Immersion immersion) {
        immersionRepository.save(immersion);
    }

    @Transactional
    public void delete(Immersion immersion) {
        immersionRepository.delete(immersion);
    }

    public boolean checkId(String Id) {
        Optional<Immersion> immersionFromDb = immersionRepository.findById(Id);
        return !immersionFromDb.isPresent();
    }

    public Immersion getById(String Id) {
        Optional<Immersion> immersionFromDb = immersionRepository.findById(Id);
        return immersionFromDb.orElse(new Immersion());

    }
    public List<ImmersionStatistic> countImmersionMonth(int month) {

        return immersionRepository.countImmersionMonth(month);

    }

}

