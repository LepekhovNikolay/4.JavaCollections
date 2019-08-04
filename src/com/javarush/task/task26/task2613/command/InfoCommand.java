package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.*;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

class InfoCommand implements Command{


    @Override
    public void execute() throws InterruptOperationException {
        ResourceBundle res = ResourceBundleFactory.getResourceBundleByKey("info");
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> manipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        boolean hasMoney = false;
        for (CurrencyManipulator manip : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (manip.hasMoney()) {
                hasMoney = true;
                ConsoleHelper.writeMessage(res.getString("available.money"));
                ConsoleHelper.writeMessage(String.format("%s - %s\n", manip.getCurrencyCode(), manip.getTotalAmount()));
            }
        }
        if (!hasMoney)
            ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
