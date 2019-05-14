package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {

    private enum Type {
        LOW_CASE,
        HIGH_CASE,
        NUMBER
    }
    private final static int SIZE_OF_PASSWORD = 8;
    private final static String LOW_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private final static String NUMBERS = "0123456789";

    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        Random random = new Random();
        int[] bufferTypes = null;
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        //цикл записи в буфер опознователя цифры/большой буквы/маленькой буквы
        boolean hasNumber;
        boolean hasLowCase;
        boolean hasHighCase;
        do{
            hasNumber = false;
            hasHighCase = false;
            hasLowCase = false;
            bufferTypes = new int[SIZE_OF_PASSWORD];
            for (int i = 0; i < SIZE_OF_PASSWORD; i++) {
                bufferTypes[i] = random.nextInt(Type.values().length);
                if (bufferTypes[i] == Type.HIGH_CASE.ordinal())
                    hasHighCase = true;
                else if (bufferTypes[i] == Type.LOW_CASE.ordinal())
                    hasLowCase = true;
                else if (bufferTypes[i] == Type.NUMBER.ordinal())
                    hasNumber = true;
                else
                    System.err.println("Не правильно задан randomInt");
            }
        } while (!(hasHighCase && hasLowCase && hasNumber));
        //генерация символов
        int position = 0;
        for (int i = 0; i < SIZE_OF_PASSWORD; i++) {
            switch (bufferTypes[i]) {
                case 1:
                    position = random.nextInt(LOW_CASE_LETTERS.length());
                    result.write((int) LOW_CASE_LETTERS.toUpperCase().charAt(position));
                    break;
                case 0:
                    position = random.nextInt(LOW_CASE_LETTERS.length());
                    result.write((int) LOW_CASE_LETTERS.charAt(position));
                    break;
                case 2:
                    position = random.nextInt(NUMBERS.length());
                    result.write((int) NUMBERS.charAt(position));
                    break;
            }
        }
//        return new ByteArrayOutputStream().write(result);
        return result;
    }
}