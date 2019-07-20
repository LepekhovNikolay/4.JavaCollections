package com.javarush.task.task39.task3908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("If you want to exit, please enter word: exit");
            System.out.println("Else, please, enter string:");
            String readedString = reader.readLine();
            if (readedString.equals("exit"))
                break;
            String info = isPalindromePermutation(readedString) ? " can be palindrom" : " can't be palindrom";
            System.out.println("String: " + readedString + info);
        }
        System.out.println("Goodbye!");
    }
/*
* создаю hashmap и заношу туда все символы в качестве ключа, количество в качестве значения
* добавляю флаг - одиночный символ
* прохожу hashmap, если флаг установился только один раз или ни разу, то можно сделать палиндром
* */
    public static boolean isPalindromePermutation(String s) {
        s = s.toLowerCase();
        HashMap<Character, Integer> symbolsMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (symbolsMap.containsKey(c)) {
                symbolsMap.put(c, symbolsMap.get(c) + 1);
            }
            else
                symbolsMap.put(c, 1);
        }
        boolean hasMiddle = false;
        for (Map.Entry<Character, Integer> entry : symbolsMap.entrySet()) {
            if (entry.getValue()%2 != 0) {
                if (hasMiddle)
                    return false;
                hasMiddle = true;
            }
        }
        return true;
    }
}
