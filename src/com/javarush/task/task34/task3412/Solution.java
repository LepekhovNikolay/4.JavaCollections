package com.javarush.task.task34.task3412;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashSet;

/* 
Добавление логирования в класс
*/

public class Solution {
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private int value1;
    private String value2;
    private Date value3;

    public Solution(int value1, String value2, Date value3) {
        logger.debug("constructor");
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public static void main(String[] args) {

    }

    public void calculateAndSetValue3(long value) {
        logger.trace("method calculateAndSetValue3 input");
        value -= 133;
        if (value > Integer.MAX_VALUE) {
            value1 = (int) (value / Integer.MAX_VALUE);
            logger.debug("method calculateAndSetValue3");
        } else {
            value1 = (int) value;
            logger.debug("method calculateAndSetValue3");
        }
    }

    HashSet
    public void printString() {
        if (value2 != null) {
            logger.trace("value2.length print");
            System.out.println(value2.length());
        }
    }

    public void printDateAsLong() {
        if (value3 != null) {
            logger.trace("value3.getTime print");
            System.out.println(value3.getTime());
        }
    }

    public void divide(int number1, int number2) {
        try {
            logger.trace("number1/number2 result print");
            System.out.println(number1 / number2);
        } catch (ArithmeticException e) {
            logger.error("divide method", e);
        }
    }

    public void setValue1(int value1) {
        logger.debug("method setValue1");
        this.value1 = value1;
    }

    public void setValue2(String value2) {
        logger.debug("method setValue2");
        this.value2 = value2;
    }

    public void setValue3(Date value3) {
        logger.debug("method setValue3");
        this.value3 = value3;
    }
}
