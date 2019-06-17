package com.javarush.task.task38.task3804;

public class ExceptionFactory {
    public static Throwable getException(Enum arg) {
        if (arg != null) {
            String message = arg.name().charAt(0) + arg.name().substring(1).toLowerCase().replace("_", " ");
            if (arg instanceof ApplicationExceptionMessage) {
                return new Exception(message);
            }
            if (arg instanceof DatabaseExceptionMessage) {
                return new RuntimeException(message);
            }
            if (arg instanceof UserExceptionMessage) {
                return new Error(message);
            }
        }
        return new IllegalArgumentException();
    }
}
