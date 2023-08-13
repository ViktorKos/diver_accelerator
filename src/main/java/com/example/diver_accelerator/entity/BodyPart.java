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
public class BodyPart {

    @Id
    @Size(max = 100)
    private String id;
    private String name;

    @OneToMany
    public List<Symptom> symptoms = new ArrayList<>();

    public void addSymptom(Symptom symptom) {
        this.symptoms.add(symptom);
    }
}
