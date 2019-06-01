package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.*;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        String string1 = "text";
        String string2 = Helper.generateRandomString();
        String string3 = "text";
        Long long1 = shortener.getId(string1);
        Long long2 = shortener.getId(string2);
        Long long3 = shortener.getId(string3);
        Assert.assertNotEquals(long1, long2);
        Assert.assertEquals(long1, long3);
        string1 = shortener.getString(long1);
        string2 = shortener.getString(long2);
        string3 = shortener.getString(long3);
        Assert.assertNotEquals(string1, string2);
        Assert.assertEquals(string1, string3);
    }
    @Test
    public void testHashMapStorageStrategy() {
        StorageStrategy strategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashMapStorageStrategy() {
        StorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testFileStorageStrategy() {
        StorageStrategy strategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testHashBiMapStorageStrategy() {
        StorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testDualHashBidiMapStorageStrategy() {
        StorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashBiMapStorageStrategy() {
        StorageStrategy strategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
}
