
package com.javarush.task.task31.task3105;
/*
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

*/
/*
Добавление файла в архив
*//*

public class Solution {

    private static final int SYSTEMBUFFERSIZE = 1024;

    public static void main(String[] args) throws IOException {
        //получаю файл из того, что имеется
        Path file = Paths.get(args[0]);
        //копирую данные из zip-архива в мапу
        Map<String, ByteArrayOutputStream> zipArchiveStore = new HashMap<>();
        try(ZipInputStream zipInput = new ZipInputStream(new FileInputStream(args[1]))) {
            ZipEntry entry;
            while ((entry = zipInput.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[SYSTEMBUFFERSIZE];
                int count = 0;
                while ((count = zipInput.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer,0, count);
                }
                zipArchiveStore.put(entry.getName(), byteArrayOutputStream);
            }
        }
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(args[1]))) {
            //записываю в архив обратно данные
            int count = 0;

            //добавляю мой файл
            ZipEntry myFile = new ZipEntry("new/" + file.getFileName());
            zipOutputStream.putNextEntry(myFile);
            Files.copy(file, zipOutputStream);
            for (Map.Entry<String, ByteArrayOutputStream> pair :
                    zipArchiveStore.entrySet()) {
//                if (pair.getKey().substring(pair.getKey().lastIndexOf("/") + 1).equals(file.getFileName())) continue;
                zipOutputStream.putNextEntry(new ZipEntry(pair.getKey()));
                zipOutputStream.write(pair.getValue().toByteArray());
            }
        }

    }
}


*/


//todo


import java.io.*;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.zip.ZipEntry;
        import java.util.zip.ZipInputStream;
        import java.util.zip.ZipOutputStream;

/*
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        // Это для отладки
//    args = new String[2];
//    args[0] = "D:\\Temp3\\javarush\\task3105\\888_6.jpg";
//    args[1] = "D:\\Temp3\\javarush\\task3105\\task3105.zip";

        // На всякий случай: если аргументов меньше 2 - можно не продолжать у нас для этого не достаточно данных.
        if (args.length < 2) {
            System.out.println("Error");
            return;
        }

        // Файл архива
        String archiveFile = args[1];
        // Файл который надо добавить в этот архив
        String newFile = args[0];

        Path newFilePath = Paths.get(newFile);
        // Это путь нового (добавляемого) файла в архиве
        // Например "new/result.mp3"
        String newFileZipPath = "new/" + newFilePath.getFileName();

        // Список Entries архива
        List<ZipEntry> entries = new ArrayList<>();
        // "Мапа" Entry -> Данные, про ByteArray см. внизу этого файла
        Map<ZipEntry, ByteArray> map = new HashMap<>();

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(archiveFile))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (newFileZipPath.equals(zipEntry.getName())) {
                    zis.closeEntry();
                } else {
                    entries.add(zipEntry);
                    // Почему 8192?
                    // Видел в классе BufferedReader (Ctrl + b на названии класса или метода в IDEA)
                    byte[] buff = new byte[8192];
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int len;
                    while ((len = zis.read(buff)) != -1) {
                        baos.write(buff, 0, len);
                    }
                    map.put(zipEntry, new ByteArray(baos.toByteArray()));
                    //zis.closeEntry(); // Not needed, it simply reads out data
                }
            }
        }

        // Это было для отладки
//    for (ZipEntry entry : entries) {
//      System.out.println(entry.getName() + " - " + map.get(entry).getData().length);
//    }

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(archiveFile))) {
            // Если архив созданный архиватором 7Zip прочитать при помощи
            // ZipInputStream то там будут Entry для папок, но валидатору JR
            // не важно писать в архив такую Entry или нет. Если я правильно
            // понимаю - '/' как раз указывает на то, что Entry - папка - но
            // это не точно, это упоминалось на Stackoverflow.
//      zos.putNextEntry(new ZipEntry("new/"));
//      zos.closeEntry();

            zos.putNextEntry(new ZipEntry(newFileZipPath));
            Files.copy(newFilePath, zos);
            zos.closeEntry();

            for (ZipEntry entry : entries) {
                zos.putNextEntry(new ZipEntry(entry.getName()));
                zos.write(map.get(entry).getData());
                zos.closeEntry();
            }
        }

    }

    //---

    /**
     * Простенький класс-обёртка для массива байт, чтобы их
     * можно было поместить в коллекцию Map<ZipEntry, ByteArray>
     */

    private static class ByteArray {
        private final byte[] data;
        public ByteArray(byte[] data) {
            this.data = data;
        }
        public byte[] getData() {
            return data;
        }
    }

}