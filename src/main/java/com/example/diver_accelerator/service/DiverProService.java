package com.example.diver_accelerator.service;

import com.example.diver_accelerator.entity.*;
import com.example.diver_accelerator.repository.DiverProRepository;
import com.example.diver_accelerator.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
public class DiverProService {

    @Autowired
    private DiverProRepository diverProRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Transactional
    public List<DiverPro> allDiverPros() {
        return diverProRepository.findAll();
    }

    @Transactional
    public void add(DiverPro diverPro) {
        diverProRepository.save(diverPro);
    }

    @Transactional
    public void delete(DiverPro diverPro) {
        diverProRepository.delete(diverPro);
    }

    public DiverPro getById(Long id) {
        DiverPro diverPro = null;
        try {
            Optional<DiverPro> diverProFromDb = diverProRepository.findById(id);
            diverPro = diverProFromDb.orElse(new DiverPro());
        } catch (NoResultException nre) {

        }

        return diverPro;
    }

    public List<DiverPro> diverProBySpecialization(Specialization specialization) {
        List<DiverPro> diverPros = null;
        try {
            diverPros = diverProRepository.findBySpecialization(specialization);
        } catch (NoResultException nre) {

        }
        return diverPros;
    }

    public List<DiverPro> diverProByNotSpecialization(Specialization specialization) {
        List<DiverPro> diverPros = null;
        try {
            diverPros = diverProRepository.findBySpecializationNot(specialization);
        } catch (NoResultException nre) {

        }
        return diverPros;
    }


    public List<DiverPro> findTelephone_number(int telephone_number) {
        List<DiverPro> diverPros = null;
        try {
            diverPros = diverProRepository.findDocTelephoneNumber(telephone_number);
        } catch (NoResultException nre) {

        }
        return diverPros;
    }

    public DiverPro diverProByUser(User user) {
        DiverPro diverPro = null;
        try {
            diverPro = diverProRepository.findByUser(user);
        } catch (NoResultException nre) {

        }

        return diverPro;

    }

    public boolean checkId(Long id) {
        Optional<DiverPro> diverProFromDb = diverProRepository.findById(id);
        return !diverProFromDb.isPresent();

    }

    public Schedule getScheduleById(int id_schedule) {

        Schedule schedule = null;
        try {
            Optional<Schedule> scheduleFromDb = scheduleRepository.findById(id_schedule);
            schedule = scheduleFromDb.orElse(new Schedule());
        } catch (NoResultException nre) {

        }
        return schedule;
    }

    @Transactional
    public void deleteSchedule(int id_schedule) {
        scheduleRepository.deleteById(id_schedule);
    }
}
