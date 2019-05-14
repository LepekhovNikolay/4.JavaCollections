package com.javarush.task.task32.task3202;

import java.io.*;

/**
Читаем из потока
Реализуй логику метода getAllDataFromInputStream. Он должен вернуть StringWriter, содержащий все данные из переданного потока.
Возвращаемый объект ни при каких условиях не должен быть null.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("C:/1/1.txt"));
        System.out.println(writer.toString());
    }
        public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
            StringWriter sw = new StringWriter();
            if (is != null) {
                InputStreamReader isr = new InputStreamReader(is);
                char[] buff = new char[1024];
                int n;
                while ((n = isr.read(buff)) > 0) {
                    sw.write(buff, 0, n);
                }
            }
            return sw;
        }
    }