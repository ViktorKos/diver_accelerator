package com.example.diver_accelerator.repository;

import com.example.diver_accelerator.entity.TestsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTypeRepository extends JpaRepository<TestsType, String> {
    TestsType findTestsTypeById(String id);
}