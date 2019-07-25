package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        int arg = 9;
        System.out.printf("%d - это степень 3. %b%n", arg, isPowerOfThree(arg));
        arg = 81;
        System.out.printf("%d - это степень 3. %b%n", arg, isPowerOfThree(arg));
        arg = 72;
        System.out.printf("%d - это степень 3. %b%n", arg, isPowerOfThree(arg));
        arg = 2;
        System.out.printf("%d - это степень 3. %b%n", arg, isPowerOfThree(arg));
    }

    public static boolean isPowerOfThree(int n) {
        if (n == 1)
            return true;
        int power = 3;
        int i = 3;
        while(true) {
            if (n < i)
                return false;
            else if (n == i)
                return true;
            i *=3;
        }
    }
}
