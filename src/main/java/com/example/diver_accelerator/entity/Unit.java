package com.example.diver_accelerator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Unit {
    @Id
    @Size(max=100)
    private String id;
    private String name;

    @OneToMany(mappedBy = "unit")
    private List<DiveCenter> diveCentersList = new ArrayList<>();

    public void addDiveCenter(DiveCenter diveCenter) {
        diveCenter.setUnit(this);
        this.diveCentersList.add(diveCenter);
    }

    public void removeDiveCenter(DiveCenter diveCenter) {
        diveCenter.setUnit(null);
        this.diveCentersList.remove(diveCenter);
    }
}
