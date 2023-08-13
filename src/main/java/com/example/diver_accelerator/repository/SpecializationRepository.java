package com.example.diver_accelerator.repository;

import com.example.diver_accelerator.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {

}
