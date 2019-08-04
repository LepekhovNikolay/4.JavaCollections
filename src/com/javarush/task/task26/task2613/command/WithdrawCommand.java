package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command{

    private Locale enUsLocale = new Locale("en", "US");

    private ResourceBundle res =
            ResourceBundle.getBundle( CashMachine.class.getPackage().getName() +".resources.withdraw_en", enUsLocale);


//    +before=Withdrawing...
//    +success.format=%d %s was withdrawn successfully
//    +specify.amount=Please specify integer amount for withdrawing.
//    +specify.not.empty.amount=Please specify valid positive integer amount for withdrawing.
//    +not.enough.money=Not enough money on your account, please try again
//    +exact.amount.not.available=Exact amount is not available

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        String amountString;
        Map<Integer, Integer> withdrawMap = new HashMap<>();
        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            amountString = ConsoleHelper.readString();
            if (!amountString.matches("^[1-9]\\d*")) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
            else if (manipulator.isAmountAvailable(Integer.parseInt(amountString))) {
                try {
                    withdrawMap = manipulator.withdrawAmount(Integer.parseInt(amountString));
                    break;
                } catch (NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                }
            } else
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : withdrawMap.entrySet()) {
            sum += entry.getKey() * entry.getValue();
            ConsoleHelper.writeMessage(String.format("\t%d - %d", entry.getKey(), entry.getValue()));
        }

        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, manipulator.getCurrencyCode()));

    }
}
