package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

class ExitCommand implements Command {
    private Locale enUsLocale = new Locale("en", "US");

    private ResourceBundle res =
            ResourceBundle.getBundle( CashMachine.class.getPackage().getName() +".resources.exit_en", enUsLocale);

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String command = ConsoleHelper.readString();
        if (command.equals("y"))
            ConsoleHelper.writeMessage(res.getString("thank.message"));

    }
}
