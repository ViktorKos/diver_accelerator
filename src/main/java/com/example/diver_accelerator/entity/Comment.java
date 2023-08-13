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
public class Comment {
    @Id
    @Size(max=100)
    private String id;
    private String text;
    private Date date;

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d_com")
    private DiverPro d_com;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diver")
    private Diver diver;


}
