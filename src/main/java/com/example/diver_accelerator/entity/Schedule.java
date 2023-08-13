package com.example.diver_accelerator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "schedule")
public class Schedule {

    @Id
    private int id;
    private int day;
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diverPro", referencedColumnName = "id")
    private DiverPro diverPro;

    public DiverPro getDiverPro() {
        return diverPro;
    }

    public void setDiverPro(DiverPro diverPro) {
        this.diverPro = diverPro;
    }

    public String getDayName() {
        String name = null;
        switch (this.day) {
            case 1: {
                name = "Sunday";
                break;
            }
            case 2: {
                name = "Monday";
                break;
            }
            case 3: {
                name = "Tuesday";
                break;
            }
            case 4: {
                name = "Wednesday";
                break;
            }
            case 5: {
                name = "Thursday";
                break;
            }
            case 6: {
                name = "Friday";
                break;
            }
            case 7: {
                name = "Saturday";
                break;
            }
        }

        return name;
    }

}
