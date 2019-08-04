package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

class InfoCommand implements Command{

    private Locale enUsLocale = new Locale("en", "US");

    private ResourceBundle res =
            ResourceBundle.getBundle( CashMachine.class.getPackage().getName() +".resources.info_en", enUsLocale);
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> manipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        boolean hasMoney = false;
        for (CurrencyManipulator manip : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (manip.hasMoney()) {
                hasMoney = true;
                ConsoleHelper.writeMessage("Available money is:");
                ConsoleHelper.writeMessage(String.format("%s - %s\n", manip.getCurrencyCode(), manip.getTotalAmount()));
            }
        }
        if (!hasMoney)
            ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
