package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.*;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command{

    @Override
    public void execute() throws InterruptOperationException {
        ResourceBundle res = ResourceBundleFactory.getResourceBundleByKey("withdraw");
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
