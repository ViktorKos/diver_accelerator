package com.example.diver_accelerator.transientClasses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Recipe extends Document {

    private List<Equipment> equipmentList = new ArrayList<>();

    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
    }
}
