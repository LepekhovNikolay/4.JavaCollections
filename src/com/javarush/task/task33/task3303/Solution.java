package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file1 = new File(fileName);
        return (T) mapper.readValue(file1, clazz);
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.weight = 3;
        ObjectMapper mapper = new ObjectMapper();
        Cat cat1 = null;
        try {
            File file = new File("C:/1/1.txt");
            mapper.writeValue(file, cat);
             cat1 = convertFromJsonToNormal(file.getAbsolutePath(), Cat.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(cat1);
    }

    @JsonPropertyOrder({"name","weight"})
    @JsonAutoDetect
    public static class Cat {
        @JsonProperty("wildAnimal")
        public String name;

        @JsonIgnore
        public int age;

        @JsonProperty("over")
        public int weight;

        Cat() {
        }

        @Override
        public String toString() {
            return String.format("Class - %s, name - %s, age - %d, weight - %d, count of Method - %d", this.getClass().getSimpleName(), name, age, weight, this.getClass().getDeclaredFields().length);
        }
    }
}
