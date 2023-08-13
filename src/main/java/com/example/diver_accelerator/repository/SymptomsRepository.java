package com.example.diver_accelerator.repository;

import com.example.diver_accelerator.entity.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomsRepository extends JpaRepository<Symptom, String> {

}

