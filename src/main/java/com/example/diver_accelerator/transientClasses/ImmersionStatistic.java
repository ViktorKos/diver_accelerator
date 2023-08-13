package com.example.diver_accelerator.transientClasses;

import com.example.diver_accelerator.entity.Immersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImmersionStatistic {
    Immersion immersion;
    Long count;
}
