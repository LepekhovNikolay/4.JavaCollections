package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {

    private static Map<String, CurrencyManipulator> map = new HashMap<>();//currencyCode/Manipulator

    private CurrencyManipulatorFactory() {
    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        currencyCode = currencyCode.toUpperCase();
        if (map.containsKey(currencyCode))
            return map.get(currencyCode);
        CurrencyManipulator manipulator = new CurrencyManipulator(currencyCode);
        map.put(currencyCode, manipulator);
        return manipulator;
    }
}
