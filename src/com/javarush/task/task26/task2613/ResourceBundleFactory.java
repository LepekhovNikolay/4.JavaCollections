package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ResourceBundleFactory {

    private static Map<String, ResourceBundle> resourceBundleMap = new HashMap<>();

    public static ResourceBundle getResourceBundleByKey(String key) {
        if (resourceBundleMap.containsKey(key))
            return resourceBundleMap.get(key);
        ResourceBundle resourceBundle = ResourceBundle.getBundle( CashMachine.RESOURCE_PATH + key, CashMachine.getCashMashineLocale());
        resourceBundleMap.put(key, resourceBundle);
        return resourceBundle;
    }
}
