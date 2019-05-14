package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor <Path> {

    private int countOfDirectory = -1;
    private int countOfFiles = 0;
    private long sizeOfContent = 0;

    public int getCountOfDirectory() {
        return countOfDirectory;
    }

    public int getCountOfFiles() {
        return countOfFiles;
    }

    public long getSizeOfContent() {
        return sizeOfContent;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        countOfDirectory++;
        return FileVisitResult.CONTINUE;

    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        countOfFiles++;
        sizeOfContent += Files.size(file);
        return FileVisitResult.CONTINUE;
    }
}
