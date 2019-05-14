package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

/* 
Что внутри папки?
*/
public class Solution {


    public static void main(String[] args) throws IOException {
        String myPathString = null;
        try {
            SimpleFileVisitor simpleFileVisitor = new MyFileVisitor();
            Path myPath = Paths.get((new BufferedReader(new InputStreamReader(System.in))).readLine());
            if (Files.isDirectory(myPath)) {
                Files.walkFileTree(myPath, simpleFileVisitor);
                System.out.printf("Всего папок - %d%n", ((MyFileVisitor) simpleFileVisitor).getCountOfDirectory());
                System.out.printf("Всего файлов - %d%n", ((MyFileVisitor) simpleFileVisitor).getCountOfFiles());
                System.out.printf("Общий размер - %s%n", convertSizes(((MyFileVisitor) simpleFileVisitor).getSizeOfContent()));
            }else {
                System.out.printf("%s - не папка", myPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convertSizes(long size) {
        long tempSize = size;
        int pow = 1024;
        int powNameIndex = 0;
        String[] powName = {"", "k", "M", "G", "T"};
        for (int i = 1; i < powName.length; i++) {
            size = tempSize;
            if ((tempSize /= pow) < 1) {
                powNameIndex = i;
                break;
            }
        }
        return size + powName[powNameIndex] + "B";
//        return Long.toString(size);
    }
}
