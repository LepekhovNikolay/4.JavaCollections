package com.javarush.task.task40.task4012;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/* 
Полезные методы DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        //метод Высокосный год
        String date = "2004-12-03";
        System.out.println("current date is " + date);
        System.out.println(isLeap(LocalDate.parse(date)) ? "current date is leap" : "current date isn't leap");
        System.out.println("");
        //метод Дата до
        String dateTime = "2019-08-24T10:15:30";
        System.out.println("dateTime " + dateTime + " is " + (isBefore(LocalDateTime.parse(dateTime)) ? "before" : "after") + " now");
        System.out.println("");
        //метод Добавить врем
        LocalTime localTime = LocalTime.parse("10:15:30");
        System.out.println(addTime(localTime, 45, ChronoUnit.MINUTES));
        //метод получить период между
        System.out.println(getPeriodBetween(LocalDate.parse(date), LocalDate.now()).getYears());
    }

    public static boolean isLeap(LocalDate date) {
        return date.isLeapYear();
    }

    public static boolean isBefore(LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now());
    }

    public static LocalTime addTime(LocalTime time, int n, ChronoUnit chronoUnit) {
        return time.plus(n, chronoUnit);
    }

    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {
        if (firstDate.isBefore(secondDate)){
            return Period.between(firstDate, secondDate);
        }else return Period.between(secondDate, firstDate);
    }
}
