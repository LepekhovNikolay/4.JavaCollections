package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {

    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";

    private static Locale cashMashineLocale = new Locale("en", "US");

    public static Locale getCashMashineLocale() {
        return cashMashineLocale;
    }

    public static void setCashMashineLocale(Locale cashMashineLocale1) {
        cashMashineLocale = cashMashineLocale1;
    }

    public static void main(String[] args) throws InterruptOperationException{
        Locale.setDefault(Locale.ENGLISH);
        String command;
        Operation operation;
        try {
            CommandExecutor.execute(Operation.LOGIN);
            do{
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
            CommandExecutor.execute(Operation.EXIT);
        }

    }

}
