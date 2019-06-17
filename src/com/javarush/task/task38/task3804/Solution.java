package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {
        //Тест занял больше времени, чем реализация функционала
        try {
            Class<?> clazz = getFactoryClass();
            Constructor<?> constructor = clazz.getConstructor();
            Object object = constructor.newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            Throwable exception = new Exception("Without result");
            for (Method method : methods) {
                int modifiers = method.getModifiers();
                if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)){
                    exception = (Throwable) method.invoke(null, ApplicationExceptionMessage.SOCKET_IS_CLOSED);
                    break;
                }
            }
            System.out.println(exception.getClass().getSimpleName());
            System.out.println(exception.getMessage());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}