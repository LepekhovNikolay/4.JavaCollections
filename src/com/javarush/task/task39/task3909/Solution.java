package com.javarush.task.task39.task3909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Одно изменение
*/

/**
 * tests:
 * 1234 & 12367
 * "" & "1"
 * "1" & ""
 * afa & aga
 * aa & aa
 * afa & aa
 *
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("If you want to exit, please enter word: exit");
            System.out.println("Else, please, enter first string:");
            String readedString1 = reader.readLine();
            if (readedString1.equals("exit"))
                break;
            System.out.println("If you want to exit, please enter word: exit");
            System.out.println("Else, please, enter second string:");
            String readedString2 = reader.readLine();
            if (readedString2.equals("exit"))
                break;
            String info = isOneEditAway(readedString1, readedString2) ? " can be change" : " can't be change";
            System.out.println("String: " + readedString1 + info + " to " + readedString2);
        }
        System.out.println("Goodbye!");
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first == null || second == null)
            return false;
        if (first.length() == 0 && second.length() == 0)
            return true;
        if (first.length() == 0 && second.length() == 1 ||
                first.length() == 1 && second.length() == 0)
            return true;
        if (Math.abs(first.length() - second.length()) > 1)
            return false;
        //начинаем проверки
        boolean hasOneEdit = false;
        int firstCount = 0;
        int secondCount = 0;
        while (true) {
            if (first.charAt(firstCount) == second.charAt(secondCount)) {
                firstCount++;
                secondCount++;
                if (firstCount == first.length() || secondCount == second.length())
                    return true;//значит мы вышли за длину строки
            } else {
                //если символы не одинаковые, то выставляем флаг и ищем одинаковые поблизости, если нет return false
                if (firstCount == first.length() || secondCount == second.length())
                    return true;
                //пробегу доп. методом (реверсом было бы дольше) все три случая удалив по одному символу и все предыдущие также
                if (first.substring(firstCount).equals(second.substring(secondCount + 1)) ||
                        first.substring(firstCount + 1).equals(second.substring(secondCount)) ||
                        first.substring(firstCount + 1).equals(second.substring(secondCount + 1)))
                    return true;
                else
                    return false;
            }
        }
    }
}
