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
public class Declaration {
    @Id
    @Size(max=100)
    private String id;
    private Date date;
    private Boolean consent;
    private String document_identifier;
// TODO----------
 @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "electronic_card", referencedColumnName = "ID")
    private Diver diver;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diverPro", referencedColumnName = "ID")
    private DiverPro diverPro_dec;

}
