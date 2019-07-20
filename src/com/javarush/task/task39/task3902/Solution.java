package com.javarush.task.task39.task3902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Биты были биты
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Please enter a number: ");

            long number = Long.parseLong(reader.readLine());
            if (number == 0)
                break;
            long time = System.currentTimeMillis();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                myIsWeightEven(number);
            }
            System.out.println("my time is " + System.currentTimeMillis() + "ms");
            time = System.currentTimeMillis();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                myIsWeightEven(number);
            }
            System.out.println("system time is " + System.currentTimeMillis() + "ms");
            System.out.println(myIsWeightEven(number) ? "my even" : "my odd");
            System.out.println(isWeightEvenStandart(number) ? "standart even" : "standart odd");
        }

    }

    //ошибку нашел только после того, как нашел стандартный способ подсчета:)
    public static boolean myIsWeightEven(long number) {
        long oper1 = 0x5555_5555;
        long oper2 = 0x3333_3333;
        long oper3 = 0x0f0f_0f0f;
        long oper4 = 0x00ff_00ff;

        long source = number;
        number = ((number >> 1)  & oper1) + (number  & oper1);
        number = ((number >> 2)  & oper2) + (number  & oper2);
        number = ((number >> 4)  & oper2) + (number  & oper2);
        number = ((number >> 8)  & oper2) + (number  & oper2);
        number += number >> 16;
        if (number%2 == 0) {
            return true;
        }
        return false;

    }

    public static boolean isWeightEvenStandart(long number) {
        if (Long.bitCount(number) % 2 == 0) {
            return true;
        }
        return false;


    }
}
