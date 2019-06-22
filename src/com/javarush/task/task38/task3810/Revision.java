package com.javarush.task.task38.task3810;

import java.util.ArrayList;
import java.util.List;

public @interface Revision {
    //напиши свой код
    int revision();

    Date date();

    Author[] authors() default {};

    String comment() default "";
}
