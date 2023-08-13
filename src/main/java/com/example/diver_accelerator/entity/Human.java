package com.example.diver_accelerator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "human")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Human {

    @Id
    protected Long id;
    protected String name;
    protected String surname;
    protected String middle_name;
    protected int telephone_number;
    protected Date date_of_birth;
    protected int sex;
    protected String address;
    protected String email;

}

