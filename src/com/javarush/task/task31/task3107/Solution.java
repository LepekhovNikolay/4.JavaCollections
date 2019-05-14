package com.javarush.task.task31.task3107;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Null Object Pattern
*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {
        Path path = null;
        try {
            path = Paths.get(pathToFile);
            boolean isHidden = Files.isHidden(path);
            boolean isExecutable = Files.isExecutable(path);
            fileData = new ConcreteFileData(isHidden, isExecutable, Files.isDirectory(path), Files.isWritable(path));
        } catch (Exception e) {
            fileData = new NullFileData(e);
        }
    }

    public FileData getFileData() {
        return fileData;
    }

    public static void main(String[] args) {
        Solution solution = new Solution("C:/1/yandex19-logo-ru.png");
        if (solution.getFileData() instanceof ConcreteFileData)
            System.out.println("ContrecteFileData");
        else if (solution.getFileData() instanceof NullFileData)
            System.out.println("NullFileData");
    }
}
