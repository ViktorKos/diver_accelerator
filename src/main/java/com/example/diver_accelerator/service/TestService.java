package com.example.diver_accelerator.service;

import com.example.diver_accelerator.entity.Test;
import com.example.diver_accelerator.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Transactional
    public void add(Test test) {
        testRepository.save(test);
    }

}
