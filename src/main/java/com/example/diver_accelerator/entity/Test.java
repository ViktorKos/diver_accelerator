package com.example.diver_accelerator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Test {

    @Id
    @Size(max = 100)
    private String id;
    private Date date;
    private String result;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testsType", referencedColumnName = "id")
    private TestsType testsType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "electronic_card", referencedColumnName = "id")
    private Diver diver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diverPro", referencedColumnName = "id")
    private DiverPro doc;

}
