package com.example.diver_accelerator.repository;

import com.example.diver_accelerator.entity.Diver;
import com.example.diver_accelerator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiverRepository extends JpaRepository<Diver, Long> {
    Diver findByUser(User user);
    @Query("SELECT d FROM Diver d WHERE d.telephone_number = :telephone_number")
    List<Diver> findDiverTelephoneNumber(@Param("telephone_number") int telephone_number);
}
