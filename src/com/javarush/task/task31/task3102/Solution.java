package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        //+логика: Добавляю директорию в список directories,
        //+захожу в цикл, выход из которого - пустой directories, при этом удаляю текущий файл из directories
        //+прохожу в текущей директории все входимости: файлы добавляю в список - result,
        //+директории добавляю в список - directories.
        //+возвращаюсь в начало
        //result возвращаю
        List<String> result = new ArrayList<>();
        List<File> directories = new ArrayList<>();
        directories.add(new File(root));
        while (directories.size() > 0) {
            File[] files = directories.get(directories.size() - 1).listFiles();
            directories.remove(directories.size() - 1);
            for (File file :
                    files) {
                if (file.isDirectory())
                    directories.add(file);
                else if (file.isFile())
                    result.add(file.getAbsolutePath());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            getFileTree("C:/1").forEach(file -> System.out.println(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
