package com.example.diver_accelerator.service;

import com.example.diver_accelerator.entity.Direction;
import com.example.diver_accelerator.entity.Diver;
import com.example.diver_accelerator.entity.Specialization;
import com.example.diver_accelerator.repository.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DirectionService {

    @Autowired
    private DirectionRepository directionRepository;


    @Transactional
    public void add(Direction direction) {
        directionRepository.save(direction);
    }

    @Transactional
    public void delete(Direction direction) {
        directionRepository.delete(direction);
    }

    public Direction getById(String id) {
        Optional<Direction> directionFromDb = directionRepository.findById(id);
        return directionFromDb.orElse(new Direction());

    }

    public List<Direction> getActiveBySpecializationAndDiver(Specialization specialization, Diver diver) {
        return directionRepository.findBySpecializationAndStatusAndDiver(specialization, true, diver);
    }
}
