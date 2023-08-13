package com.example.diver_accelerator.repository;

import com.example.diver_accelerator.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, String> {

}
