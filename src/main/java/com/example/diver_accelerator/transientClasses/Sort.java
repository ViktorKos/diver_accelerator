package com.example.diver_accelerator.transientClasses;

import com.example.diver_accelerator.entity.*;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

@NoArgsConstructor
public class Sort {

    public List<User> sortUser(List<User> users, int i) {
        switch (i) {
            case 1: {
                users.sort(Comparator.comparing(User::getUsername));
                break;
            }
            case 2: {
                users.sort(Comparator.comparing(User::isSelected));
                break;
            }
        }
        return users;
    }
    //sortImmersion
    public List<Immersion> sortImmersion(List<Immersion> immersions, int i) {
        switch (i) {
            case 1: {
                immersions.sort(Comparator.comparing(Immersion::getMax_deep));
                break;
            }
            case 2: {
                immersions.sort(Comparator.comparing(Immersion::getLocation));
                break;
            }

        }

        return immersions;
    }
    //sortSpecialization
    public List<Specialization> sortSpecialization(List<Specialization> specializations, int i) {
        switch (i) {
            case 1: {
                specializations.sort(Comparator.comparing(Specialization::getId));
                break;
            }
            case 2: {
                specializations.sort(Comparator.comparing(Specialization::getName));
                break;
            }

        }

        return specializations;
    }

    public List<Role> sortRole(List<Role> roles, int i) {
        switch (i) {
            case 1: {
                roles.sort(Comparator.comparing(Role::getId));
                break;
            }
            //sortSpecialization
            case 2: {
                roles.sort(Comparator.comparing(Role::getName));
                break;
            }
        }

        return roles;
    }

    public List<DiverPro> sortDiverPro(List<DiverPro> diverPros, int i) {
        switch (i) {
            case 1: {
                diverPros.sort(Comparator.comparing(DiverPro::getId));
                break;
            }
            case 2: {
                diverPros.sort(Comparator.comparing(DiverPro::getSurname));
                break;
            }

            case 3: {
                diverPros.sort(Comparator.comparing(DiverPro::getSex));
                break;
            }

            case 4: {
                diverPros.sort(Comparator.comparing(DiverPro::getTelephone_number));
                break;
            }

            case 5: {
                diverPros.sort(Comparator.comparing(o -> o.getSpecialization().getName()));
                break;
            }

        }
        return diverPros;
    }

    public List<Schedule> sortSchedule(List<Schedule> schedules, int i) {
        switch (i) {
            case 1: {
                schedules.sort(Comparator.comparing(Schedule::getId));
                break;
            }
            case 2: {
                schedules.sort(Comparator.comparing(Schedule::getDayName).thenComparing(Schedule::getTime));
                break;
            }

            case 3: {
                schedules.sort(Comparator.comparing(Schedule::getTime));
                break;
            }
        }

        return schedules;
    }

    public List<Diver> sortDiver(List<Diver> divers, int i) {
        switch (i) {
            case 1: {
                divers.sort(Comparator.comparing(Diver::getId));
                break;
            }
            case 2: {
                divers.sort(Comparator.comparing(Diver::getSurname));
                break;
            }

            case 3: {
                divers.sort(Comparator.comparing(Diver::getSex));
                break;
            }

            case 4: {
                divers.sort(Comparator.comparing(Diver::getTelephone_number));
                break;
            }

        }

        return divers;
    }

    public List<Visit> sortVisit(List<Visit> visits, int i) {
        switch (i) {
            case 1: {
                visits.sort(Comparator.comparing(Visit::getNumber));
                break;
            }
            case 2: {
                visits.sort(Comparator.comparing(Visit::getDate));
                break;
            }

            case 3: {
                visits.sort(Comparator.comparing(o -> o.getImmersion().getLocation()));
                break;
            }

            case 4: {
                visits.sort(Comparator.comparing(o -> o.getDiverPro().getSpecialization().getName()));
                break;
            }

            case 5: {
                visits.sort(Comparator.comparing(o -> o.getSchedule().getTime()));
                break;
            }

            case 6: {
                visits.sort(Comparator.comparing(Visit::getStatus));
                break;
            }

        }

        return visits;
    }
    public List<Direction> sortDirection(List<Direction> directions, int i) {
        switch (i) {
            case 1: {
                directions.sort(Comparator.comparing(Direction::getNumber));
                break;
            }
            case 2: {
                directions.sort(Comparator.comparing(o -> o.getSpecialization().getName()));
                break;
            }

            case 3: {
                directions.sort(Comparator.comparing(Direction::getStatus));
                break;
            }

        }

        return directions;
    }
    public List<Unit> sortUnit(List<Unit> units, int i) {
        units.sort(Comparator.comparing(Unit::getName));

        return units;
    }

    public List<EquipmentCatalog> sortEquipmentCatalog(List<EquipmentCatalog> equipmentCatalog, int i) {
        switch (i) {
            case 1: {
                equipmentCatalog.sort(Comparator.comparing(EquipmentCatalog::getId));
                break;
            }
            case 2: {
                equipmentCatalog.sort(Comparator.comparing(EquipmentCatalog::getName));
                break;
            }

        }

        return equipmentCatalog;
    }
    //
    //sortSpecialization
    public List<StaffingScheme> sortStaffingScheme(List<StaffingScheme> staffingScheme, int i) {
        switch (i) {
            case 1: {
                staffingScheme.sort(Comparator.comparing(StaffingScheme::getNumber));
                break;
            }
            case 2: {
                staffingScheme.sort(Comparator.comparing(o -> o.getSpecialization().getName()));
                break;
            }

        }
        return staffingScheme;
    }


}

