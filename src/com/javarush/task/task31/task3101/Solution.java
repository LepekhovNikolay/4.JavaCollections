package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        File directory = new File(args[0]);
        File destination = new File(args[1]);
        List<File> filesFromDir = foundFiles(directory);
        List<File> filesLess50 =foundFilesLess50(filesFromDir);
        Collections.sort(filesLess50, new FileNameComparator());
        File newDestination = new File(destination.getParent() +"/allFilesContent.txt");
//        if (!FileUtils.isExist(new File(destination.getParent() +"/allFilesContent.txt")))
            FileUtils.renameFile(destination, newDestination);
        summingFiles(newDestination, filesLess50);
    }

    //возвращает все файлы, входящие в путь
    private static List<File> foundFiles(File source) {
        //+логика: Добавляю директорию в список directories,
        //+захожу в цикл, выход из которого - пустой directories, при этом удаляю текущий файл из directories
        //+прохожу в текущей директории все входимости: файлы добавляю в список - result,
        //+директории добавляю в список - directories.
        //+возвращаюсь в начало
        //result возвращаю
        List<File> result = new ArrayList<>();
        List<File> directories = new ArrayList<>();
        directories.add(source);
        while (directories.size() > 0) {
          File[] files = directories.get(directories.size() - 1).listFiles();
          directories.remove(directories.size() - 1);
            for (File file :
                    files) {
                if (file.isDirectory())
                    directories.add(file);
                else if (file.isFile())
                    result.add(file);
            }
        }
        return result;
    }

    //возвращает список с файлами меньше 50 байт
    private static List<File> foundFilesLess50(List<File> sourceList) {
        List<File> result = new ArrayList<>();
        for (File currentFile :
                sourceList) {
            if (currentFile.length() <= 50)
                result.add(currentFile);
        }
        return result;
    }

    //записывает список файлов в один файл с разделителем между файлами \n
    private static void summingFiles(File dest, List<File> sources) {

        try(OutputStreamWriter writer = (new OutputStreamWriter(new FileOutputStream(dest)))) {
            for (File source:
                    sources) {
                try(InputStreamReader reader = new InputStreamReader(new FileInputStream(source))) {
                    char[] buffer = new char[50];
                    int length = 0;
                    while ((length = reader.read(buffer, 0, 50)) > 0)
                        writer.write(buffer, 0, length);
                    writer.write("\n");
                    writer.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class FileNameComparator implements Comparator<File> {
        @Override
        public int compare(File o1, File o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

}

