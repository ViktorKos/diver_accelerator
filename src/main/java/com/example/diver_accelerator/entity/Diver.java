package com.example.diver_accelerator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "electronic_card")
public class Diver extends Human {


    private int blood_type;
    private String rh;
    private String diveLevel;
    private String certification_org;
    private String diver_id;
    private double height;
    private double width;
    private int dive_cnt;
    private int max_deep;
    private int count_of_recipe;
    private int count_of_recreation_list;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "diver", cascade = CascadeType.ALL, orphanRemoval = true)
 public List<Visit> visits = new ArrayList<>();

    @OneToMany(mappedBy = "diver")
    public List<Direction> directions = new ArrayList<>();

    @OneToMany(mappedBy = "diver")
    public List<Test> tests = new ArrayList<>();
//todo


    @OneToMany(mappedBy = "diver")
    public List<Rate> rates = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "declaration", referencedColumnName = "id")
    private Declaration declaration;
    @OneToMany
    public List<SymptomsHistory> symptomsHistories = new ArrayList<>();

    public List<User> getUsers(List<User> allUsers, int id) {
        List<User> diverUsers = new ArrayList<>();

            for (User user : allUsers) {
                Optional<Role> c_role = user.getRoles().stream().findFirst();
                if (c_role.orElse(new Role()).getId() == id && !user.isSelected()) {
                    diverUsers.add(user);
                }
            }
        return diverUsers;
    }

    public List<Visit> getVisits() {
        visits.sort(Collections.reverseOrder(Comparator.comparing(Visit::getDate)));
        return visits;
    }

  public List<Specialization> getSpecializationDoneVisits() {
        List<Specialization> specializations = new ArrayList<>();
        for (Visit visit : this.getDoneVisits(1)) {

            if (!specializations.contains(visit.getDiverPro().getSpecialization())) {
                specializations.add(visit.getDiverPro().getSpecialization());
            }

        }
        return specializations;

    }

    public List<Immersion> getImmersionDoneVisits() {
        List<Immersion> immersion = new ArrayList<>();
        for (Visit visit : this.getDoneVisits(1)) {

            if (!immersion.contains(visit.getImmersion())) {
                immersion.add(visit.getImmersion());
            }

        }
        return immersion;
    }


    public List<Visit> getDoneVisits(int i) {
        List<Visit> done = new ArrayList<>();
        for (Visit visit : this.visits) {
            if (visit.getStatus()) {
                done.add(visit);
            }

        }

        switch (i) {
            case 1: {
                try {
                    done.sort(Comparator.comparing(Visit::getDate).thenComparing(o -> o.getSchedule().getTime()).reversed());
                } catch (Exception e) {
                    done.sort(Comparator.comparing(Visit::getDate).reversed());
                }
                break;
            }
            case 2: {
                done.sort(Comparator.comparing(o -> o.getImmersion().getLocation()));
                break;
            }
            case 3: {
                done.sort(Comparator.comparing(o -> o.getDiverPro().getSpecialization().getName()));
                break;
            }

            case 4: {
                done.sort(Comparator.comparing(Visit::getEquipment, Comparator.nullsLast(Comparator.reverseOrder()))
                        .thenComparing(Visit::getEquipment));

                break;
            }

        }

        return done;
    }

    public List<Visit> getActiveVisits() {
        List<Visit> active = new ArrayList<>();
        for (Visit visit : this.visits) {
            if (!visit.getStatus()) {
                active.add(visit);
            }
        }
        active.sort(Comparator.comparing(Visit::getDate).thenComparing(o -> o.getSchedule().getTime()));
        return active;
    }

    public List<Visit> expiredVisits() {
        List<Visit> expired = this.getActiveVisits();
        for (Visit visit : this.visits) {
            if (visit.getDate().equals(Date.valueOf(LocalDate.now().toString())) || visit.getDate().after(Date.valueOf(LocalDate.now().toString()))) {
                expired.remove(visit);
            }
        }
        return expired;
    }

    public void removeExpiredVisits() {
        List<Visit> expired = this.expiredVisits();
        for (Visit visit : expired) {
            this.visits.remove(visit);
        }
    }

    public boolean findActiveVisitBySpecialization(Specialization specialization) {
        for (Visit visit : this.visits) {
            if ((!visit.getStatus()) && visit.getDiverPro().getSpecialization().getName().equals(specialization.getName())) {
                return true;
            }
        }
        return false;
    }

    public void addVisit(Visit visit) {

        visit.setDiver(this);
        this.visits.add(visit);
    }

    public Visit findVisit(String id) {
        for (Visit visit : visits) {
            if (visit.getNumber().equals(id)) {
                return visit;
            }
        }

        return null;
    }

    public Test findTest(String id) {
        for (Test test : tests) {
            if (test.getId().equals(id)) {
                return test;
            }
        }

        return null;
    }

    public String getDateToString() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date_of_birth);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        return day + "." + month + "." + year;
    }
    public boolean isDirectionExists(String specialization) {
        List<Direction> active = this.getActiveDirectionsWithoutTests();
        for (Direction direction : active) {
            if (direction.getStatus() && direction.getSpecialization().getName().equals(specialization)) {
                return false;
            }
        }

        return true;
    }
    public List<Direction> getActiveDirectionsWithoutTests() {
        List<Direction> active = new ArrayList<>();
        for (Direction direction : this.directions) {
            if (direction.getSpecialization().getId() != 8) {
                if (direction.getStatus()) {
                    active.add(direction);
                }
            }

        }
        return active;
    }

    public List<Direction> getActiveDirectionsWithTests() {
        List<Direction> active = new ArrayList<>();
        for (Direction direction : this.directions) {
            if (direction.getSpecialization().getId() == 8) {
                if (direction.getStatus()) {
                    active.add(direction);
                }
            }

        }
        return active;
    }
    public Direction findActiveDirection(Specialization specialization) {

        for (Direction direction : this.directions) {
            if (direction.getStatus() && direction.getSpecialization().getName().equals(specialization.getName())) {
                return direction;
            }
        }

        return null;
    }


    public Direction findNotActiveDirection(Specialization specialization) {

        for (Direction direction : this.directions) {
            if (!direction.getStatus() && direction.getSpecialization().getName().equals(specialization.getName())) {
                return direction;
            }
        }

        return null;
    }

    public boolean isTestTypeExists(String testType) {
        List<Direction> active = this.getActiveDirectionsWithTests();
        for (Direction direction : active) {
            if (direction.getStatus() && direction.getTestsType().getName().equals(testType)) {
                return false;
            }
        }

        return true;
    }
    public SymptomsHistory findSymptom(String id) {
        for (SymptomsHistory record : symptomsHistories) {
            if (record.getId().equals(id)) {
                return record;
            }
        }

        return null;
    }

    public Rate getRateByDiverPro(DiverPro diverPro) {

        for (Rate rate : this.rates) {
            if (Objects.equals(rate.getD_rate(), diverPro)) {
                return rate;
            }
        }
        return null;
    }
}
