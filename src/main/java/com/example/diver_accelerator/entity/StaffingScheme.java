package com.example.diver_accelerator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class StaffingScheme {

    @Id
    @Size(max = 100)
    private String number;
    private double count;
    private double maxCount;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialization", referencedColumnName = "id")
    private Specialization specialization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diveCenters")
    private DiveCenter diveCenter_s;

    public void setCountDueToMaxCount(int countOfDiverPros) {
        this.setCount(this.getMaxCount() - countOfDiverPros);
    }
}
