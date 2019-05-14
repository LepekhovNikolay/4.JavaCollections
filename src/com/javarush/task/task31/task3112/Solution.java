package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
//        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("C:/1"));
        Path passwords = downloadFile("https://yastatic.net/morda-logo/i/citylogos/yandex19-logo-ru.png", Paths.get("C:/1"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        //Метод downloadFile должен создавать объект URL для переданной ссылки.
        URL url = new URL(urlString);
        //Метод downloadFile должен создать временный файл с помощью метода Files.createTempFile.
        Path tempFile = Files.createTempFile("temp", "");
        //Метод downloadFile должен скачать файл по ссылке во временный файл, используя метод Files.copy.
        Files.copy(url.openStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
        //4. Метод downloadFile должен переместить файл из временной директории в пользовательскую, используя метод Files.move.
        //5. Имя сохраненного файла должно быть таким же, как в URL-ссылке.
        String fileName = urlString.substring(urlString.lastIndexOf("/"));
        Path resultFile = Paths.get(downloadDirectory.toString(), fileName);
        Files.move(tempFile, resultFile, StandardCopyOption.REPLACE_EXISTING);
        return resultFile;
    }
}
