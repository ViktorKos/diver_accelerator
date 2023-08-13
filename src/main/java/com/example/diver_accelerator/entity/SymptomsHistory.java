package com.example.diver_accelerator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SymptomsHistory {

    @Id
    @Size(max = 100)
    private String id;
    private Date date;
    private String symptoms;
    private String notes;
}
