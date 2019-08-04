package com.javarush.task.task26.task2613;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;
    public static Operation getAllowableOperationByOrdinal(Integer i) {
        try {
            if (i == 0)
                throw new Exception();
            return Operation.values()[i];
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
