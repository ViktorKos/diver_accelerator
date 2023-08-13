package com.example.diver_accelerator.repository;


import com.example.diver_accelerator.entity.Direction;
import com.example.diver_accelerator.entity.Diver;
import com.example.diver_accelerator.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, String> {

    List<Direction> findBySpecializationAndStatus(Specialization specialization, Boolean status);
    List<Direction> findBySpecializationAndStatusAndDiver(Specialization specialization, Boolean status, Diver diver);
}
