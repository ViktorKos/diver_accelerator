package com.example.diver_accelerator.repository;

import com.example.diver_accelerator.entity.DiveCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DiveCenterRepository extends JpaRepository<DiveCenter, String> {
    List<DiveCenter> findByUnitNull();

}