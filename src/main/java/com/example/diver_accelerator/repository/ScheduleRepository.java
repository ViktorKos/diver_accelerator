package com.example.diver_accelerator.repository;

import com.example.diver_accelerator.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

}
