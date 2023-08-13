package com.example.diver_accelerator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class DiveCenter {

    @Id
    @Size(max=100)
    private String id;
    private String name;

    @OneToMany(mappedBy = "diveCenter_s", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffingScheme> staffingSchemes = new ArrayList<>();

    @OneToMany(mappedBy = "diveCenter")
    private List<DiverPro> diverPros = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit")
    private Unit unit;

    public List<DiverPro> findByTelephone(int telephone) {
        List<DiverPro> diverProsByTelephone = new ArrayList<>();
        for (DiverPro diverPro : this.getDiverPros()) {
            if (diverPro.getTelephone_number() == telephone) {
                diverProsByTelephone.add(diverPro);
            }
        }

        return diverProsByTelephone;
    }

    public void addDiverPro(DiverPro diverPro) {
        this.changeCountDueToSpecialization(diverPro.getSpecialization(), -1);
        diverPro.setDiveCenter(this);
        this.diverPros.add(diverPro);
    }

    public void removeDiverPro(DiverPro diverPro) {
        this.changeCountDueToSpecialization(diverPro.getSpecialization(), 1);
        diverPro.setDiveCenter(null);
        this.diverPros.remove(diverPro);
    }

    public void addStaffingScheme(StaffingScheme staffingScheme) {
        staffingScheme.setDiveCenter_s(this);
        this.staffingSchemes.add(staffingScheme);
    }

    public void removeStaffingScheme(String id_staffingScheme) {
        this.staffingSchemes.removeIf(staffingScheme -> staffingScheme.getNumber().equals(id_staffingScheme));
    }

    public List<Specialization> getSpecializationListByScheme() {
        List<Specialization> specializations = new ArrayList<>();
        for (StaffingScheme staffingScheme : this.staffingSchemes) {
            if (staffingScheme.getCount() > 0) {
                if (!specializations.contains(staffingScheme.getSpecialization())) {
                    specializations.add(staffingScheme.getSpecialization());
                }
            }
        }

        return specializations;
    }
    public List<DiverPro> findBySpecialization(Specialization specialization) {
        List<DiverPro> diverPros = new ArrayList<>();
        for (DiverPro diverPro : this.getDiverPros()) {
            if (diverPro.getSpecialization().equals(specialization)) {
                diverPros.add(diverPro);
            }
        }

        return diverPros;
    }
    public void changeCountDueToSpecialization(Specialization specialization, int i) {
        double num = 0;
        for (StaffingScheme staffingScheme : this.staffingSchemes) {
            if (staffingScheme.getSpecialization().equals(specialization)) {
                num = staffingScheme.getCount() + i;
                if (staffingScheme.getMaxCount() >= num && num >= 0) {
                    staffingScheme.setCount(num);
                }
            }
        }
    }

}
