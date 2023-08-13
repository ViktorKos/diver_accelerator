package com.example.diver_accelerator.transientClasses;

import com.example.diver_accelerator.entity.Immersion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class Recreation_list extends Document {

    private Date start_date;
    private Immersion start_immersion;
    private String Dive_site;
    private String contact;

}
