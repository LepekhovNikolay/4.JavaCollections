package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Please enter your string: ");
            String s = bufferedReader.readLine();
            if (s.equals("exit"))
                break;

            System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
        }
    }

    /*
    поиск вперед, если нашли повторяющийся символ, то backSearch и начинаем поиск заново
     */
    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;
        Set<Character> subStringSet = new HashSet<>();
        int maxLength = 1;
        int curLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (subStringSet.contains(s.charAt(i))) {
                //проверка максимальной подстроки, поиск назад и продолжаем искать след. строку
                if (curLength > maxLength) {
                    maxLength = curLength;
                }

                i = backSearch(i, s);

                subStringSet.clear();
                curLength = 0;
            }
            subStringSet.add(s.charAt(i));
            curLength++;
        }
        if (curLength > maxLength)
            maxLength = curLength;
        return maxLength;
    }

    //реализация обратного поиска
    private static int backSearch(int curPos, String s) {
        char curChar = s.charAt(curPos);
        for (int i = curPos-1; i >= 0; i--) {
             if (s.charAt(i) == curChar)
                 return ++i;
        }

        if (curPos < s.length() - 1)
            return ++curPos;
        else
            return s.length() - 1;
    }
}
