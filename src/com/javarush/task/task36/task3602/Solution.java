package com.javarush.task.task36.task3602;

import java.lang.reflect.*;
import java.util.Collections;
import java.util.List;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class[] clazzes = Collections.class.getDeclaredClasses();
        for (Class clazz : clazzes) {
            if (List.class.isAssignableFrom(clazz)) {
                if (isPrivateStatic(clazz)) {
                    Constructor constructor = null;
                    List list = null;
                    try {
                        constructor = clazz.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        list = (List) constructor.newInstance();
                        list.get(0);
                    } catch (IndexOutOfBoundsException e) {
                        return clazz;
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    private static boolean isListChild(Class clazz) {
        AnnotatedType[] interfaces = clazz.getAnnotatedInterfaces();
        for (AnnotatedType anInterface : interfaces) {
            if(anInterface.getType().getTypeName().equals("java.util.List<E>"))
                return true;
        }
        return false;
    }

    private static boolean isPrivateStatic(Class clazz){
        int mod = clazz.getModifiers();
        boolean isPrivate = Modifier.isPrivate(mod);
        boolean isStatic = Modifier.isStatic(mod);
        if (isPrivate && isStatic)
            return true;
        return false;
    }
}
