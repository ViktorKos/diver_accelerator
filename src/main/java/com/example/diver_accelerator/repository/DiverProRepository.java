package com.example.diver_accelerator.repository;


import com.example.diver_accelerator.entity.DiverPro;
import com.example.diver_accelerator.entity.Specialization;
import com.example.diver_accelerator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DiverProRepository extends JpaRepository<DiverPro, Long> {

    List<DiverPro> findBySpecialization(Specialization specializations);

    List<DiverPro> findBySpecializationNot(Specialization specializations);

    DiverPro findByUser(User user);

    @Query("SELECT d FROM DiverPro d WHERE d.telephone_number = :telephone_number")
    List<DiverPro> findDocTelephoneNumber(@Param("telephone_number") int telephone_number);

}
