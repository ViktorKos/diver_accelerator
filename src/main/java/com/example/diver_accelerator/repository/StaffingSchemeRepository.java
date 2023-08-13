package com.example.diver_accelerator.repository;

import com.example.diver_accelerator.entity.StaffingScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffingSchemeRepository extends JpaRepository<StaffingScheme, String> {

}