package com.example.diver_accelerator.repository;

import com.example.diver_accelerator.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, String> {

}
