package com.example.diver_accelerator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "immersion")
public class Immersion {

    @Id
    @Size(max = 100)
    private String max_deep;
    private String location;
}
