package com.example.diver_accelerator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Symptom {

    @Id
    @Size(max = 100)
    private String id;
    private String name;
}
