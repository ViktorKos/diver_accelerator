package com.example.diver_accelerator.service;


import com.example.diver_accelerator.entity.BodyPart;
import com.example.diver_accelerator.repository.BodyPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BodyPartService {

    @Autowired
    private BodyPartRepository bodyPartRepository;

    public List<BodyPart> allBodyPart() {
        return bodyPartRepository.findAll();
    }

    @Transactional
    public void delete(BodyPart bodyPart) {
        bodyPartRepository.delete(bodyPart);
    }


    public BodyPart getById(String id) {
        Optional<BodyPart> immersionFromDb = bodyPartRepository.findById(id);
        return immersionFromDb.orElse(new BodyPart());
    }

    public void addBodyPart(BodyPart bodyPart) {
        bodyPartRepository.save(bodyPart);
    }
}
