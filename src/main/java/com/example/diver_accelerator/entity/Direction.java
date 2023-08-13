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
@Table(name = "direction")
public class Direction {

    @Id
    @Size(max=100)
    private String number;
    private Boolean status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialization", referencedColumnName = "id")
    private Specialization specialization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "electronic_card", referencedColumnName = "id")
    private Diver diver;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testsType", referencedColumnName = "id")
    private TestsType testsType;

}
