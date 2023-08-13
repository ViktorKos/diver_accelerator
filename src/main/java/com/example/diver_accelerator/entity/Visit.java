package com.example.diver_accelerator.entity;

import com.example.diver_accelerator.transientClasses.Equipment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="visit")
public class Visit {

    @Id
    @Size(max = 100)
    private String number;
    private Date date;
    private String actions;
    private String complaints;
    private String notes;
    private Boolean status;
    private String equipment;


   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "electronic_card", referencedColumnName = "id")
    private Diver diver;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "diverPro", referencedColumnName = "id")
        private DiverPro diverPro;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "schedule")
        private Schedule schedule;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "max_deep")
    private Immersion immersion;

    //проверка на пустоту
    public void setEquipmentByList(List<Equipment> equipments) {
        StringBuilder eq = new StringBuilder();

        for (Equipment equipment : equipments) {
            if (eq.length() != 0) {
                eq.append(", ").append(equipment.getName());
            } else {
                eq.append(equipment.getName());
            }
        }

        this.equipment = equipments.toString();
    }

    public String getDateToString() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        return day + "." + month + "." + year;
    }

    public boolean getIsTodayVisit() {
        return this.getDate().equals(Date.valueOf(LocalDate.now().toString()));
    }

}
