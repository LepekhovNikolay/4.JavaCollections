package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/*
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("9.10.2017 5:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        //напишите тут ваш код
        DateTimeFormatter formatter = null;
        boolean isDate = false;
        boolean isTime = false;
        boolean isFullDate = false;
        if (date.matches("[\\d\\.]+")) {
            isDate = true;
        } else if (date.matches("[\\d:]+")) {
            isTime = true;
        } else if (date.matches("[\\d.]+ [\\d:]+")){
            isFullDate = true;
        } else {
            return;
        }
        try {
            if (isDate || isFullDate) {
                formatter = DateTimeFormatter.ofPattern("d.M.y");
                LocalDate localDate = null;
                if (isFullDate)
                    localDate = LocalDate.parse(date.split(" ")[0], formatter);
                else
                    localDate = LocalDate.parse(date, formatter);
                System.out.println("День: " + localDate.getDayOfMonth());
                System.out.println("День недели: " + localDate.getDayOfWeek().getValue());
                System.out.println("День месяца: " + localDate.getDayOfMonth());
                System.out.println("День года: " + localDate.getDayOfYear());
                System.out.println("Неделя месяца: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth()));
                System.out.println("Неделя года: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
                System.out.println("Месяц: " + localDate.getMonthValue());
                System.out.println("Год: " + localDate.getYear());
            }
            if (isTime || isFullDate) {
                formatter = DateTimeFormatter.ofPattern("H:m:s");

                LocalTime localDate = null;
                if (isFullDate)
                    localDate = LocalTime.parse(date.split(" ")[1], formatter);
                else
                    localDate = LocalTime.parse(date, formatter);

                System.out.println("AM или PM: " + (localDate.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM"));
                System.out.println("Часы: " + localDate.get(ChronoField.HOUR_OF_AMPM));
                System.out.println("Часы дня: " + localDate.getHour());
                System.out.println("Минуты: " + localDate.getMinute());
                System.out.println("Секунды: " + localDate.getSecond());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
