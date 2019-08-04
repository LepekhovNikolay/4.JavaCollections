package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

class DepositCommand implements Command{

    private Locale enUsLocale = new Locale("en", "US");

    private ResourceBundle res =
            ResourceBundle.getBundle( CashMachine.class.getPackage().getName() +".resources.deposit_en", enUsLocale);

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());

        int[] ints = stringToInt(
                ConsoleHelper.getValidTwoDigits(manipulator.getCurrencyCode())
        );
        manipulator.addAmount(ints[0], ints[1]);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), ints[0] * ints[1], manipulator.getCurrencyCode()));
    }

    private int[] stringToInt(String[] strings) {
        int[] ints = new int[strings.length];
        try {
            for (int i = 0; i < strings.length; i++) {
                ints[i] = Integer.parseInt(strings[i]);
            }
        } catch (NumberFormatException e) {
            for (int anInt : ints) {
                anInt = 0;
            }
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
        return ints;
    }

}
