package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        if (strings == null)
            throw new IllegalArgumentException();
        long startTime = System.currentTimeMillis();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        return System.currentTimeMillis() - startTime;
    }
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings)  {
        Date startTime = new Date();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        Date endTime = new Date();
        return endTime.getTime() - startTime.getTime();
    }

    @Test
    public void testHashMapStorage() {
        int SIZE = 1000;
        StorageStrategy strategy1 = new HashMapStorageStrategy();
        StorageStrategy strategy2 = new HashBiMapStorageStrategy();
        Shortener shortener1 = new Shortener(strategy1);
        Shortener shortener2 = new Shortener(strategy2);
        Set<String> origStrings = new HashSet<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> ids1 = new HashSet<>(SIZE);
        Set<Long> ids2 = new HashSet<>(SIZE);
        long timeGetIdsStrat1 = getTimeToGetIds(shortener1, origStrings, ids1);
        long timeGetIdsStrat2 = getTimeToGetIds(shortener2, origStrings, ids2);
        Assert.assertTrue(timeGetIdsStrat1 > timeGetIdsStrat2);
        long timeGetStringsStrat1 = getTimeToGetStrings(shortener1, ids1, new HashSet<>());
        long timeGetStringsStrat2 = getTimeToGetStrings(shortener2, ids2, new HashSet<>());
        Assert.assertEquals(timeGetStringsStrat1, timeGetStringsStrat2, 30);
     }
}
