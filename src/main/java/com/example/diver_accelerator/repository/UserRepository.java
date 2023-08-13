package com.example.diver_accelerator.repository;


import com.example.diver_accelerator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
