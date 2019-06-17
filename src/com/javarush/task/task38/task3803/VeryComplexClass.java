package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.io.File;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object x = 1;
        System.out.println((String)x);
    }

    public void methodThrowsNullPointerException() {
        File file = new File((String)null);
    }

    public static void main(String[] args) {
//        new VeryComplexClass().methodThrowsClassCastException();
        new VeryComplexClass().methodThrowsNullPointerException();
    }
}
