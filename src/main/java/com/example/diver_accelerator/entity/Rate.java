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
public class Rate {
    @Id
    @Size(max=100)
    private String id;
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d_rate")
    private DiverPro d_rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diver")
    private Diver diver;
}