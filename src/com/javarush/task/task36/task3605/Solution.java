package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        final int BUF_SIZE=1024;
        try (Reader reader = new FileReader(new File(args[0]))) {
            char[] buffer = new char[BUF_SIZE];
            int length = 0;
            TreeSet<Character> letterTree = new TreeSet<>();
            while ((length = reader.read(buffer, 0, BUF_SIZE)) != -1) {
                for (int i = 0; i < length; i++) {
                    if (Character.isLetter(buffer[i]))
                        letterTree.add(Character.toLowerCase(buffer[i]));
                }
            }
            length = 0;
            while (length++ < 5){
                Character letter = letterTree.pollFirst();
                if (letter == null)
                    break;
                System.out.print(letter);
            }
        }
    }
}
