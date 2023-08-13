package com.example.diver_accelerator.transientClasses;

import com.example.diver_accelerator.entity.DiveCenter;
import com.example.diver_accelerator.entity.DiverPro;
import com.example.diver_accelerator.entity.Specialization;

import java.sql.Date;
import java.util.*;

public class ControllerMainTools {


    public static String dateToString(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        return day + "." + month + "." + year;
    }

    public static Long getIdDiverSplit(String id_visit) {
        String[] words = id_visit.split("_");
        return Long.parseLong(words[1]);
    }

    public static Date currentDate() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        return new Date(currentDate.getTime());
    }

    public static List<DiverPro> firstMinusSecondArraysDiverPro(List<DiverPro> firstArrayList, List<DiverPro> secondArrayList) {
        Set<DiverPro> firstArray = new HashSet<>(firstArrayList);
        Set<DiverPro> secondArray = new HashSet<>(secondArrayList);

        firstArray.removeAll(secondArray);
        return new ArrayList<>(firstArray);
    }

    public static List<DiveCenter> firstMinusSecondArraysDiveCenter(List<DiveCenter> firstArrayList, List<DiveCenter> secondArrayList) {
        Set<DiveCenter> firstArray = new HashSet<>(firstArrayList);
        Set<DiveCenter> secondArray = new HashSet<>(secondArrayList);

        firstArray.removeAll(secondArray);
        return new ArrayList<>(firstArray);
    }

    public static List<Specialization> firstMinusSecondArraysSpecialization(List<Specialization> firstArrayList, List<Specialization> secondArrayList) {
        Set<Specialization> firstArray = new HashSet<>(firstArrayList);
        Set<Specialization> secondArray = new HashSet<>(secondArrayList);

        firstArray.removeAll(secondArray);
        return new ArrayList<>(firstArray);
    }


}
