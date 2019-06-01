package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Long SIZE_OF_TEST = 100_000L;
//        testStrategy(new HashMapStorageStrategy(), SIZE_OF_TEST);
//        testStrategy(new OurHashMapStorageStrategy(), SIZE_OF_TEST);
//        testStrategy(new FileStorageStrategy(), SIZE_OF_TEST);
        testStrategy(new OurHashBiMapStorageStrategy(), SIZE_OF_TEST);
        testStrategy(new HashBiMapStorageStrategy(), SIZE_OF_TEST);
        testStrategy(new DualHashBidiMapStorageStrategy(), SIZE_OF_TEST);

    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> resultSet = new HashSet<>();
        for (String string : strings) {
            resultSet.add(shortener.getId(string));
        }
        return resultSet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> resultSet = new HashSet<>();
        for (Long key : keys) {
            resultSet.add(shortener.getString(key));
        }
        return resultSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> testStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testStrings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Date startTime = new Date();
        Set<Long> ids = getIds(shortener, testStrings);
        Helper.printMessage(String.format("метод getIds c %d элементами выполнился за %d мс",
                elementsNumber,
               new Date().getTime() - startTime.getTime()));
        startTime = new Date();
        Set<String> strings = getStrings(shortener, ids);
        Helper.printMessage(String.format("метод getStrings c %d элементами выполнился за %d мс",
                elementsNumber,
                new Date().getTime() - startTime.getTime()));
        if(testStrings.size() == strings.size() && strings.containsAll(testStrings))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }
}
