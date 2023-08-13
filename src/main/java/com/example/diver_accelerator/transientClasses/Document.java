package com.example.diver_accelerator.transientClasses;

import com.example.diver_accelerator.entity.Visit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class Document {

    private Visit visit;

}
