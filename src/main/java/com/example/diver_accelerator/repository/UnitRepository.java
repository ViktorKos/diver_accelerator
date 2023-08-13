package com.example.diver_accelerator.repository;

import com.example.diver_accelerator.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, String> {

}
