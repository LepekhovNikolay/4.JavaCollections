package com.javarush.task.task35.task3512;

import java.lang.reflect.InvocationTargetException;

public class Generator<T> {

    private Class<T> clazz;

    public Generator(Class<T> clazz) {
        this.clazz = clazz;
    }

    T newInstance() {
        try {
            return clazz.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
