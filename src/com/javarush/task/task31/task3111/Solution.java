package com.javarush.task.task31.task3111;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

/* 
Продвинутый поиск файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        new Thread(){
            @Override
            public void run() {
                Gui myGui = new Gui();
            }
        }.start();
        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
        searchFileVisitor.setMaxSize(100);
        searchFileVisitor.setPartOfName(".txt");
        searchFileVisitor.setPartOfContent("132");

        Date start = new Date();
        Files.walkFileTree(Paths.get("C:/1"), searchFileVisitor);
        System.out.println(new Date().getTime() - start.getTime());
        List<Path> foundFiles = searchFileVisitor.getFoundFiles();
        if (foundFiles == null)
            System.out.println("Files with your parameters not found");
        else
            for (Path file : foundFiles) {
                System.out.println(file);
            }
    }

}
