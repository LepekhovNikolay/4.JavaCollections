package com.javarush.task.task26.task2613;

import net.bytebuddy.asm.Advice;

import java.util.Locale;

public enum Language {
    ENGLISH,
    RUSSIAN;
    public static Locale getLocale (Language language) throws IllegalArgumentException{
        switch (language) {
            case ENGLISH:
                return new Locale("en", "US");
            case RUSSIAN:
                return new Locale("ru", "RU");
        }
        throw new IllegalArgumentException();
    }
}
